package com.yassir.bitbox.Services.Item;

import com.yassir.bitbox.dto.item.ItemDTO;
import com.yassir.bitbox.dto.item.PriceReductionDTO;
import com.yassir.bitbox.dto.item.SupplierDTO;
import com.yassir.bitbox.enums.ItemStateEnum;
import com.yassir.bitbox.models.Item.Item;
import com.yassir.bitbox.models.Item.PriceReduction;
import com.yassir.bitbox.models.Item.Supplier;
import com.yassir.bitbox.models.user.User;
import com.yassir.bitbox.repositories.IItemRepository;
import com.yassir.bitbox.repositories.IPriceReductionRepository;
import com.yassir.bitbox.repositories.ISupplierRepository;
import com.yassir.bitbox.repositories.IUserRepository;
import com.yassir.bitbox.utils.MapperUtility;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


@Service
public class DefaultItemService implements ItemService{
    @Autowired
    private IItemRepository itemRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ISupplierRepository supplierRepository;
    @Autowired
    private IPriceReductionRepository priceReductionRepository;


    @Override
    public List<ItemDTO> getItems(String state, String supplier) {
        List<ItemDTO> result = new ArrayList<>();
        ItemStateEnum stateEnum = null;
        if(state.equals("ACTIVE")){
            stateEnum = ItemStateEnum.ACTIVE;
        }else if(state.equals("DISCONTINUED")){
            stateEnum = ItemStateEnum.DISCONTINUED;
        }
        for(Item i: itemRepository.findByStateAndSupplier(stateEnum,supplier)){
            ItemDTO resultToAdd = MapperUtility.toItemDTO(i);
            //setting null these elements to avoid the serialization of em in this method
            resultToAdd.setSuppliers(null);
            resultToAdd.setPriceReductions(null);
            result.add(resultToAdd);
        }
        return result;
    }

    @Override
    public ItemDTO getItemByCode(Long itemCode) {
        Item item = itemRepository.findById(itemCode).orElse(null);
        if(item!=null){
            return MapperUtility.toItemDTO(item);
        }else{
            return null;
        }
    }

    @Override
    public void delete(Long itemCode) {

        if(itemCode != null){
            Item itemTemp = itemRepository.findById(itemCode).orElse(null);
            if (itemTemp != null) {
                //REMOVE ASSOCIATIONS IN THE JOIN TABLE
                itemTemp.getSuppliers().forEach(supplier -> supplier.getItems().remove(itemTemp));
                itemTemp.getSuppliers().clear();


                itemRepository.delete(itemTemp);
            }else {
                throw new HibernateException("Theres no item with such code: " + itemCode);
            }
        }else{
            throw new HibernateException("ItemCode can't be null");
        }
    }

    @Override
    public void update(Long itemCode, ItemDTO itemDTO) {
        Item itemPojo = itemRepository.findById(itemCode).orElse(null);
        if(itemPojo != null){
            //** temporary
            if (itemDTO.getDescription() != null) {
                itemPojo.setDescription(itemDTO.getDescription());
            }
            if(itemDTO.getPrice() != null){
                itemPojo.setPrice(itemDTO.getPrice());
            }
            if(itemDTO.getState() != null){
                itemPojo.setState(itemDTO.getState());
            }
            itemRepository.save(itemPojo);
        }
    }

    @Override
    public void saveItem(ItemDTO itemDTO) {
        itemDTO.setSuppliers(new HashSet<>());
        itemDTO.setPriceReductions(new HashSet<>());
        itemDTO.setCreationDate(new Date());
        //default state should be active, see if state is null to set it down
        if(itemDTO.getState() == null){
            itemDTO.setState(ItemStateEnum.ACTIVE);
        }
        //In the case of: item has a user associated by id will find the user to make the whole relation
        if(itemDTO.getCreator().getId()!=null){
            User userTemp = userRepository.findById(itemDTO.getCreator().getId()).orElse(null);
            assert userTemp != null;
            itemDTO.setCreator(MapperUtility.toUserDTO(userTemp));
        }
        itemRepository.save(MapperUtility.toItemPOJO(itemDTO));
    }

    //---------------------------------------------------------------------------------------------
    //                                 ADDERS FOR ITEMS         ++Might need to be in other service
    //---------------------------------------------------------------------------------------------

    @Override
    public void addSupplier(Long itemCode, SupplierDTO supplierDTO) {
        // Fetch the item by its ID
        Item item = itemRepository.findById(itemCode)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + itemCode));

        Supplier supplierPojo;

        if (supplierDTO.getSupplierCode() == null) {
            // New supplier (no ID provided)
            supplierPojo = MapperUtility.toSupplierPOJO(supplierDTO);
        } else {
            // Existing supplier (ID provided)
            supplierPojo = supplierRepository.findById(supplierDTO.getSupplierCode())
                    .orElseThrow(() -> new RuntimeException("Supplier not found with ID: " + supplierDTO.getSupplierCode()));
        }

        // Add the supplier to the item
        item.addSupplier(supplierPojo);
        // Add the item to the supplier to maintain bidirectional consistency
        supplierPojo.addItem(item);


        // Save changes
        supplierRepository.save(supplierPojo); // Saves the supplier
        itemRepository.save(item);  // Saves the item
    }

    @Override
    public void addDiscount(Long itemCode, PriceReductionDTO priceReductionDTO) {
        priceReductionDTO.setStartDate(new Date());
        // Fetch the item by its ID
        Item item = itemRepository.findById(itemCode)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + itemCode));

        PriceReduction priceReductionPojo;

        if (priceReductionDTO.getId()== null) {
            // New supplier (no ID provided)
            priceReductionPojo = MapperUtility.toPriceReductionPOJO(priceReductionDTO);
        } else {
            // Existing supplier (ID provided)
            priceReductionPojo = priceReductionRepository.findById(priceReductionDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Supplier not found with ID: " + priceReductionDTO.getId()));
        }

        // Add the price reduction to the item
        item.addPriceReduction(priceReductionPojo);

        // Add the item to the pricereduction to maintain bidirectional consistency
        priceReductionPojo.addItem(item);

        // Save changes
        priceReductionRepository.save(priceReductionPojo); // Saves the supplier
        itemRepository.save(item);  // Saves the item

    }
}
