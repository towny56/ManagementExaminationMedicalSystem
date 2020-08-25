package com.fixit.areas.statistic;

import com.fixit.abstractions.controller.BaseController;
import com.fixit.cache.DataWardCacheSingleton;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/statistics")
public class StatisticController extends BaseController {

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    public ModelAndView allStatus(){
        return super.view("views/statistics/all", DataWardCacheSingleton.getInstance().getStatistics());
    }
}
