package com.yassir.bitbox.Services.dblogger;

import com.yassir.bitbox.dto.dblogger.DbLoggerDTO;
import com.yassir.bitbox.models.dblogger.DbLogger;

import java.util.List;

public interface IDbLogger {
    void storageLog(DbLoggerDTO dbloggerDTO);
    List<DbLogger> getAllDbLoggerByUser(long userId);
}
