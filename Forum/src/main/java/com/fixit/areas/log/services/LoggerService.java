package com.fixit.areas.log.services;

import java.util.Set;
import com.fixit.areas.log.models.service.LogServiceModel;

public interface LoggerService {

    Set<LogServiceModel> findAllLogs();

    void create(LogServiceModel logServiceModel);

    void deleteAll();
}
