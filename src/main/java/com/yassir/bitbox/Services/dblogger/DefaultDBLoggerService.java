package com.yassir.bitbox.Services.dblogger;

import com.yassir.bitbox.dto.dblogger.DbLoggerDTO;
import com.yassir.bitbox.models.dblogger.DbLogger;
import com.yassir.bitbox.repositories.IDbLoggerRepository;
import com.yassir.bitbox.utils.MapperUtility;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultDBLoggerService implements IDbLogger{

    @Autowired
    IDbLoggerRepository loggerRepository;
    @Override
    public void storageLog(DbLoggerDTO dbloggerDTO) {
        if(dbloggerDTO != null){
            loggerRepository.save(MapperUtility.toLoggerPOJO(dbloggerDTO));
        }else{
            throw new HibernateException("Some elements are needed to save the log");
        }
    }

    @Override
    public List<DbLogger> getAllDbLoggerByUser(long userId) {
        return loggerRepository.findAllByUserId(userId);
    }
}
