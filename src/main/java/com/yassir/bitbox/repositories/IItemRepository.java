package com.yassir.bitbox.repositories;

import com.yassir.bitbox.models.Item.Item;
import com.yassir.bitbox.models.Item.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemRepository extends JpaRepository<Item, Long> {
}
