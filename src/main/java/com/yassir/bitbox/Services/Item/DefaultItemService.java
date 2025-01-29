package com.yassir.bitbox.Services.Item;

import com.yassir.bitbox.dto.item.ItemDTO;
import com.yassir.bitbox.dto.item.PriceReductionDTO;
import com.yassir.bitbox.dto.item.SupplierDTO;
import com.yassir.bitbox.dto.user.UserDTO;
import com.yassir.bitbox.models.Item.Item;
import com.yassir.bitbox.models.Item.PriceReduction;
import com.yassir.bitbox.models.Item.Supplier;
import com.yassir.bitbox.models.user.User;
import com.yassir.bitbox.repositories.IItemRepository;
import com.yassir.bitbox.repositories.IPriceReductionRepository;
import com.yassir.bitbox.repositories.ISupplierRepository;
import com.yassir.bitbox.repositories.IUserRepository;
import org.hibernate.HibernateException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


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
    @Autowired
    private ModelMapper mmapper; //= new org.modelmapper.ModelMapper();

    @Override
    public List<ItemDTO> getItems() {
        List<ItemDTO> result = new ArrayList<>();
        for(Item i: itemRepository.findAll()){
           result.add(mmapper.map(i,ItemDTO.class));
        }
        return result;
    }

    @Override
    public ItemDTO getItemByCode(Long itemCode) {
        return mmapper.map(itemRepository.findById(itemCode),ItemDTO.class);
    }

    @Override
    public void addSupplier(Long itemCode, SupplierDTO supplier) {
        //this one work creating new ones and adding it
        if(supplier.getSupplierCode()== null){
            //----------------------------------------
            // THIS OVERALL WORK
            //----------------------------------------

            Supplier suppTemp = mmapper.map(supplier,Supplier.class);

            suppTemp.addItem(itemRepository.findById(itemCode).orElse(null));


            Item itemTemp = itemRepository.findById(itemCode).orElse(null);
            assert itemTemp != null;
            itemTemp.addSupplier(suppTemp);

            supplierRepository.save(suppTemp);
            itemRepository.save(itemTemp);
        }else{

        }
    }

    @Override
    public void addDiscount(Long itemCode, PriceReductionDTO priceReduction) {
//        Item itemTemp = itemRepository.findById(itemCode).orElse(null);
//        if(itemTemp!=null){
//            itemTemp.addPriceReduction(mapper.priceReductionDTOToPriceReduction(priceReduction));
//        }else{
//            throw new HibernateException("Not item found with code: "+itemCode);
//        }
        //***-----------------------------------------------------------------
        // might need a save statement not sure if the context save the object
        //--------------------------------------------------------------------
    }

    @Override
    public void saveItem(ItemDTO item) {
        if(item!=null){
            //In the case of: item has a user associated by id will find the user to make the whole relation
            if(item.getCreator().getId()!=null){
                User userTemp = userRepository.findById(item.getCreator().getId()).orElse(null);
                item.setCreator(mmapper.map(userTemp, UserDTO.class));
            }
            itemRepository.save(mmapper.map(item,Item.class));
        }else{
            throw new HibernateException("Not null objects admited");
        }
    }
}
