package com.yassir.bitbox.repositories;

import com.yassir.bitbox.enums.ItemStateEnum;
import com.yassir.bitbox.models.Item.Item;
import com.yassir.bitbox.models.Item.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IItemRepository extends JpaRepository<Item, Long> {

//    @Query("SELECT * FROM Item i " +
//            "WHERE (:state IS NULL OR :state = '' OR i.state = :state)")
//    List<Item> findByState(@Param("state") ItemStateEnum state);
    //CREATE FILTER FOR NULL ELEMENTS
    List<Item> findByState(ItemStateEnum state);
}
