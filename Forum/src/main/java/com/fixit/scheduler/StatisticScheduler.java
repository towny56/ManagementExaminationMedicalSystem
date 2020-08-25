package com.fixit.scheduler;

import com.fixit.areas.result.services.ResultBloodService;
import com.fixit.areas.result.services.ResultIrmService;
import com.fixit.areas.result.services.ResultService;
import com.fixit.areas.users.services.UsersService;
import com.fixit.cache.DataWardCacheSingleton;
import com.fixit.cache.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StatisticScheduler {

    @Autowired
    private UsersService usersService;

    @Autowired
    private ResultService resultService;

    @Autowired
    private ResultIrmService resultIrmService;

    @Autowired
    private ResultBloodService resultBloodService;

    @Scheduled(cron = "0 */5 * ? * *")
    public void updateStatistic(){

        Statistics statistics = new Statistics();
        statistics.setCounterUsers(this.usersService.counterAllUsers());
        statistics.setCounterResults(this.resultService.counterAllResults());
        statistics.setCounterBloodResults(this.resultBloodService.counterAllResultsBlood());
        statistics.setCounterIrmResults(this.resultIrmService.counterAllResultsIrm());

        DataWardCacheSingleton.getInstance().addStatistics(statistics);
    }
}
