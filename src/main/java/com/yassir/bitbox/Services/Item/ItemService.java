package com.yassir.bitbox.Services.Item;

import com.yassir.bitbox.dto.item.ItemDTO;
import com.yassir.bitbox.dto.item.PriceReductionDTO;
import com.yassir.bitbox.dto.item.SupplierDTO;

import java.util.List;

public interface ItemService {
    List<ItemDTO> getItems();
    ItemDTO getItemByCode(Long itemCode);
    void addSupplier(Long itemCode, SupplierDTO supplier);
    void addDiscount(Long itemCode, PriceReductionDTO priceReduction);
    void saveItem(ItemDTO item);
}
