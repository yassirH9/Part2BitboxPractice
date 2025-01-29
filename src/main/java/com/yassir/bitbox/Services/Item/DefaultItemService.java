package com.yassir.bitbox.Services;

import com.yassir.bitbox.models.products.Item;
import com.yassir.bitbox.models.products.PriceReduction;
import com.yassir.bitbox.models.products.Supplier;

public class DefaultItemService implements ItemService{
    @Override
    public Item getItems() {
        return null;
    }

    @Override
    public Item getItemByCode(Long itemCode) {
        return null;
    }

    @Override
    public void addSupplier(Long itemCode, Supplier supplier) {

    }

    @Override
    public void addDiscount(Long ItemCode, PriceReduction priceReduction) {

    }

    @Override
    public void saveItem(Item item) {

    }
}
