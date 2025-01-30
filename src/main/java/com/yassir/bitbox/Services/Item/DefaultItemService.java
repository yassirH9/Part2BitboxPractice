package com.yassir.bitbox.Services.Item;

import com.yassir.bitbox.dto.item.ItemDTO;
import com.yassir.bitbox.dto.item.PriceReductionDTO;
import com.yassir.bitbox.dto.item.SupplierDTO;
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
    public List<ItemDTO> getItems() {
        List<ItemDTO> result = new ArrayList<>();
        for(Item i: itemRepository.findAll()){
           result.add(MapperUtility.toItemDTO(i));
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
//                itemTemp.getSuppliers().forEach(supplier -> supplier.getItems().remove(itemTemp));
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
    public void saveItem(ItemDTO item) {
        item.setSuppliers(new HashSet<>());
        item.setPriceReductions(new HashSet<>());
        item.setCreationDate(new Date());
        if(item!=null){
            //In the case of: item has a user associated by id will find the user to make the whole relation
            if(item.getCreator().getId()!=null){
                User userTemp = userRepository.findById(item.getCreator().getId()).orElse(null);
                assert userTemp != null;
                item.setCreator(MapperUtility.toUserDTO(userTemp));
            }
            itemRepository.save(MapperUtility.toItemPOJO(item));
        }else{
            throw new HibernateException("Not null objects admited");
        }
    }

    //---------------------------------------------------------------------------------------------
    //                                 ADDERS FOR ITEMS         ++Might need to be in other service
    //---------------------------------------------------------------------------------------------

    @Override
    public void addSupplier(Long itemCode, SupplierDTO supplier) {
        // Fetch the item by its ID
        Item item = itemRepository.findById(itemCode)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + itemCode));

        Supplier supplierPojo;

        if (supplier.getSupplierCode() == null) {
            // New supplier (no ID provided)
            supplierPojo = MapperUtility.toSupplierPOJO(supplier);
        } else {
            // Existing supplier (ID provided)
            supplierPojo = supplierRepository.findById(supplier.getSupplierCode())
                    .orElseThrow(() -> new RuntimeException("Supplier not found with ID: " + supplier.getSupplierCode()));
        }

        // Add the supplier to the item
        item.addSupplier(supplierPojo);

        // Add the item to the supplier to maintain bidirectional consistency
//        supplierPojo.addItem(item);

        // Save changes
        supplierRepository.save(supplierPojo); // Saves the supplier
        itemRepository.save(item);  // Saves the item
    }

    @Override
    public void addDiscount(Long itemCode, PriceReductionDTO priceReduction) {
        // Fetch the item by its ID
        Item item = itemRepository.findById(itemCode)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + itemCode));

        PriceReduction priceReductionPojo;

        if (priceReduction.getId()== null) {
            // New supplier (no ID provided)
            priceReductionPojo = MapperUtility.toPriceReductionPOJO(priceReduction);
        } else {
            // Existing supplier (ID provided)
            priceReductionPojo = priceReductionRepository.findById(priceReduction.getId())
                    .orElseThrow(() -> new RuntimeException("Supplier not found with ID: " + priceReduction.getId()));
        }

        // Add the price reduction to the item
        item.addPriceReduction(priceReductionPojo);

        // Add the item to the pricereduction to maintain bidirectional consistency
//        priceReductionPojo.addItem(item);

        // Save changes
        priceReductionRepository.save(priceReductionPojo); // Saves the supplier
        itemRepository.save(item);  // Saves the item

    }
}
