package com.yassir.bitbox.Services;

import com.yassir.bitbox.models.products.Item;
import com.yassir.bitbox.models.products.PriceReduction;
import com.yassir.bitbox.models.products.Supplier;

public interface ItemService {
    Item getItems();
    Item getItemByCode(Long itemCode);
    void addSupplier(Long itemCode, Supplier supplier);
    void addDiscount(Long ItemCode, PriceReduction priceReduction);
    void saveItem(Item item);
}
