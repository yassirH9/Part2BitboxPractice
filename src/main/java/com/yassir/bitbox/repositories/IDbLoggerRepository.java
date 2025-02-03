package com.yassir.bitbox.repositories;

import com.yassir.bitbox.models.dblogger.DbLogger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDbLoggerRepository extends JpaRepository<DbLogger, Long> {
    List<DbLogger> findAllByUserId(Long userId);
}
