package com.yassir.bitbox.utils;

import com.yassir.bitbox.dto.dblogger.DbLoggerDTO;
import com.yassir.bitbox.dto.item.ItemDTO;
import com.yassir.bitbox.dto.item.PriceReductionDTO;
import com.yassir.bitbox.dto.item.SupplierDTO;
import com.yassir.bitbox.dto.user.UserDTO;
import com.yassir.bitbox.models.Item.Item;
import com.yassir.bitbox.models.Item.PriceReduction;
import com.yassir.bitbox.models.Item.Supplier;
import com.yassir.bitbox.models.dblogger.DbLogger;
import com.yassir.bitbox.models.user.User;

import java.util.stream.Collectors;

public class MapperUtility {
    public static ItemDTO toItemDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemCode(item.getItemCode());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setState(item.getState());
        itemDTO.setSuppliers(item.getSuppliers().stream()
                .map(MapperUtility::toSupplierDTO)
                .collect(Collectors.toSet()));
        itemDTO.setPriceReductions(item.getPriceReductions().stream()
                .map(MapperUtility::toPriceReductionDTO)
                .collect(Collectors.toSet()));
        itemDTO.setCreationDate(item.getCreationDate());
        itemDTO.setCreator(toUserDTO(item.getCreator()));
        return itemDTO;
    }
    public static Item toItemPOJO(ItemDTO itemDTO) {
        Item item = new Item();
        item.setItemCode(itemDTO.getItemCode());
        item.setDescription(itemDTO.getDescription());
        item.setPrice(itemDTO.getPrice());
        item.setState(itemDTO.getState());
        item.setSuppliers(itemDTO.getSuppliers().stream()
                .map(MapperUtility::toSupplierPOJO)
                .collect(Collectors.toSet()));
        item.setPriceReductions(itemDTO.getPriceReductions().stream()
                .map(MapperUtility::toPriceReductionPOJO)
                .collect(Collectors.toSet()));
        item.setCreationDate(itemDTO.getCreationDate());
        item.setCreator(toUserPOJO(itemDTO.getCreator()));
        return item;
    }
    public static PriceReductionDTO toPriceReductionDTO(PriceReduction priceReduction) {
        PriceReductionDTO priceReductionDTO = new PriceReductionDTO();
        priceReductionDTO.setId(priceReduction.getId());
        priceReductionDTO.setReducedPrice(priceReduction.getReducedPrice());
        priceReductionDTO.setStartDate(priceReduction.getStartDate());
        priceReductionDTO.setFinishDate(priceReduction.getFinishDate());
        return priceReductionDTO;
    }
    public static PriceReduction toPriceReductionPOJO(PriceReductionDTO priceReductionDTO) {
        PriceReduction priceReduction = new PriceReduction();
        priceReduction.setId(priceReductionDTO.getId());
        priceReduction.setReducedPrice(priceReductionDTO.getReducedPrice());
        priceReduction.setStartDate(priceReductionDTO.getStartDate());
        priceReduction.setFinishDate(priceReductionDTO.getFinishDate());
        return priceReduction;
    }

    public static SupplierDTO toSupplierDTO(Supplier supplier) {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplierCode(supplier.getSupplierCode());
        supplierDTO.setName(supplier.getName());
        supplierDTO.setCountry(supplier.getCountry());
        return supplierDTO;
    }
    public static Supplier toSupplierPOJO(SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier();
        supplier.setSupplierCode(supplierDTO.getSupplierCode());
        supplier.setName(supplierDTO.getName());
        supplier.setCountry(supplierDTO.getCountry());
        return supplier;
    }

    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        userDTO.setPassword(user.getPassword());
        userDTO.setPrivileges(user.getPrivileges());
        return userDTO;
    }
    public static User toUserPOJO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setPrivileges(userDTO.getPrivileges());
        return user;
    }
    public static DbLogger toLoggerPOJO(DbLoggerDTO dbLoggerDTO){
        DbLogger logger = new DbLogger();
        logger.setId(dbLoggerDTO.getId());
        logger.setUser(toUserPOJO(dbLoggerDTO.getUserDTO()));
        logger.setItem(toItemPOJO(dbLoggerDTO.getItemDTO()));
        logger.setReasonMSG(dbLoggerDTO.getReasonMSG());
        return logger;
    }
    public static DbLoggerDTO toLoggerDTO(DbLogger dbLogger){
        DbLoggerDTO loggerDTO = new DbLoggerDTO();
        loggerDTO.setId(dbLogger.getId());
        loggerDTO.setUserDTO(toUserDTO(dbLogger.getUser()));
        loggerDTO.setItemDTO(toItemDTO(dbLogger.getItem()));
        loggerDTO.setReasonMSG(dbLogger.getReasonMSG());
        return loggerDTO;
    }
}
