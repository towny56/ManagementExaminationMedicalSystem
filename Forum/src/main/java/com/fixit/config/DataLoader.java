package com.fixit.config;
import com.fixit.areas.result.services.ResultBloodService;
import com.fixit.areas.result.services.ResultIrmService;
import com.fixit.areas.result.services.ResultService;
import com.fixit.areas.role.models.service.RoleServiceModel;
import com.fixit.areas.role.services.RoleService;
import com.fixit.areas.users.models.service.UsersServiceModel;
import com.fixit.areas.users.repositories.UsersRepository;
import com.fixit.areas.users.services.UsersService;
import com.fixit.areas.ward.models.view.WardNamesViewModel;
import com.fixit.areas.ward.services.WardService;
import com.fixit.cache.DataWardCacheSingleton;
import com.fixit.cache.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private UsersRepository usersRepository;
    private final UsersService usersService;
    private final RoleService roleService;
    private final WardService wardService;
    private final ResultService resultService;
    private final ResultBloodService resultBloodService;
    private final ResultIrmService resultIrmService;

    @Autowired
    public DataLoader(UsersRepository usersRepository, UsersService usersService, RoleService roleService, WardService wardService, ResultService resultService, ResultBloodService resultBloodService, ResultIrmService resultIrmService) {
        this.usersRepository = usersRepository;
        this.usersService = usersService;
        this.roleService = roleService;
        this.wardService = wardService;
        this.resultService = resultService;
        this.resultBloodService = resultBloodService;
        this.resultIrmService = resultIrmService;
    }
    public void run(ApplicationArguments args) {
        RoleServiceModel adminRole = this.roleService.findByAuthority("ADMIN");
        RoleServiceModel patientRole = this.roleService.findByAuthority("PATIENT");
        RoleServiceModel doctorRole = this.roleService.findByAuthority("DOCTOR");

        if (adminRole == null) {
            RoleServiceModel roleServiceModel = new RoleServiceModel();
            roleServiceModel.setAuthority("ADMIN");
            this.roleService.addRole(roleServiceModel);
        }

        if (patientRole == null) {
            RoleServiceModel roleServiceModel = new RoleServiceModel();
            roleServiceModel.setAuthority("PATIENT");
            this.roleService.addRole(roleServiceModel);
        }

        if (doctorRole == null) {
            RoleServiceModel roleServiceModel = new RoleServiceModel();
            roleServiceModel.setAuthority("DOCTOR");
            this.roleService.addRole(roleServiceModel);
        }

        if(usersRepository.findAll().size() == 0){
            UsersServiceModel usersServiceModel = new UsersServiceModel();
            usersServiceModel.setUsername("admin");
            usersServiceModel.setPassword("admin");
            usersServiceModel.setRoleName("ADMIN");
            usersServiceModel.setFirstName("Admin");
            usersServiceModel.setLastName("Admin");
            usersServiceModel.setEmail("admin@gmail.com");
            usersServiceModel.setEgn("00000000");
            usersServiceModel.setWard(null);
            usersService.createUser(usersServiceModel);
        }

        List<WardNamesViewModel> wardNames = new ArrayList<>();
        this.wardService.findAllWardNames().forEach(wardName -> {
            WardNamesViewModel wardNamesViewModel = new WardNamesViewModel();
            wardNamesViewModel.setWardName(wardName);
            wardNames.add(wardNamesViewModel);
        });

        DataWardCacheSingleton.getInstance().addWards(wardNames);

        Statistics statistics = new Statistics();
        statistics.setCounterUsers(this.usersService.counterAllUsers());
        statistics.setCounterResults(this.resultService.counterAllResults());
        statistics.setCounterBloodResults(this.resultBloodService.counterAllResultsBlood());
        statistics.setCounterIrmResults(this.resultIrmService.counterAllResultsIrm());

        DataWardCacheSingleton.getInstance().addStatistics(statistics);
    }
}