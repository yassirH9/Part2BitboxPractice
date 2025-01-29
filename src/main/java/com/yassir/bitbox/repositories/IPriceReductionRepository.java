package com.yassir.bitbox.repositories;

import com.yassir.bitbox.models.Item.PriceReduction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPriceReductionRepository extends JpaRepository<PriceReduction, Long> {
}
