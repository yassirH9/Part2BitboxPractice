package com.yassir.bitbox.repositories;

import com.yassir.bitbox.enums.ItemStateEnum;
import com.yassir.bitbox.models.Item.Item;
import com.yassir.bitbox.models.Item.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT DISTINCT i FROM Item i " +
            "LEFT JOIN i.suppliers s " +
            "WHERE (:state IS NULL OR :state = '' OR i.state = :state) " +
            "AND (:supplier IS NULL OR :supplier = '' OR s.name = :supplier)")
    List<Item> findByStateAndSupplier(@Param("state") ItemStateEnum state, @Param("supplier") String supplier);
}
