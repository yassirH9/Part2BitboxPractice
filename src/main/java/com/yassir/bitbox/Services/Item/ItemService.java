package com.yassir.bitbox.Services.Item;

import com.yassir.bitbox.dto.dblogger.DbLoggerDTO;
import com.yassir.bitbox.dto.item.ItemDTO;
import com.yassir.bitbox.dto.item.PriceReductionDTO;
import com.yassir.bitbox.dto.item.SupplierDTO;

import java.util.List;

public interface ItemService {
    List<ItemDTO> getItems(String state);
    ItemDTO getItemByCode(Long itemCode);
    void addSupplier(Long itemCode, SupplierDTO supplierDTO);
    void addDiscount(Long itemCode, PriceReductionDTO priceReductionDTO);
    void saveItem(ItemDTO itemDTO);
    void delete(Long itemCode);
    void update(Long itemCode, ItemDTO itemDTO);
    void changeItemState(Long itemCode, DbLoggerDTO dbLoggerDTO);
}
