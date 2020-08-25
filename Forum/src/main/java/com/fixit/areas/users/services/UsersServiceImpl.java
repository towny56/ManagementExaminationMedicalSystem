package com.fixit.areas.users.services;

import com.fixit.areas.result.models.view.ResultBloodViewModel;
import com.fixit.areas.result.models.view.ResultIrmViewModel;
import com.fixit.areas.result.services.ResultBloodService;
import com.fixit.areas.result.services.ResultIrmService;
import com.fixit.areas.role.entities.Role;
import com.fixit.areas.role.models.service.RoleServiceModel;
import com.fixit.areas.role.services.RoleService;
import com.fixit.areas.users.entities.Users;
import com.fixit.areas.users.models.service.UsersServiceModel;
import com.fixit.areas.users.models.view.UserManageViewModel;
import com.fixit.areas.users.models.view.UserMedicalRecordViewModel;
import com.fixit.areas.users.repositories.UsersRepository;
import com.fixit.areas.ward.entities.Ward;
import com.fixit.areas.ward.models.service.WardServiceModel;
import com.fixit.areas.ward.services.WardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final WardService wardService;
    private final ResultBloodService resultBloodService;
    private final ResultIrmService resultIrmService;

    @Autowired
    public UsersServiceImpl(UsersRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper, RoleService roleService, WardService wardService, ResultBloodService resultBloodService, ResultIrmService resultIrmService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.wardService = wardService;
        this.resultBloodService = resultBloodService;
        this.resultIrmService = resultIrmService;
    }

    @Override
    public void createUser(UsersServiceModel userServiceModel) {
        Users userEntity = this.modelMapper.map(userServiceModel, Users.class);
        userEntity.setPassword(this.bCryptPasswordEncoder.encode(userEntity.getPassword()));
        userEntity.setAccountNonExpired(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setCredentialsNonExpired(true);
        userEntity.setEnabled(true);

        if(userServiceModel.getEgn().isEmpty()){
            userEntity.setEgn(null);
        }

        if(userServiceModel.getFirstName().isEmpty()){
            userEntity.setFirstName(null);
        }

        if(userServiceModel.getLastName().isEmpty()){
            userEntity.setLastName(null);
        }

        RoleServiceModel roleServiceModel = this.roleService.findByAuthority(userServiceModel.getRoleName());
        Role role  = this.modelMapper.map(roleServiceModel, Role.class);

        userEntity.addRole(role);

        if(userServiceModel.getWardName() == null){
            userEntity.setWard(null);
        }
        else
        {
            WardServiceModel wardServiceModel = this.wardService.findByWardName(userServiceModel.getWardName());
            Ward ward = this.modelMapper.map(wardServiceModel, Ward.class);

            userEntity.setWard(ward);
        }

        this.userRepository.save(userEntity);
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return this.userRepository.findOneByUsername(username) != null;
    }

    @Override
    public boolean isEmailTaken(String email) {
        return this.userRepository.findByEmail(email) != null;
    }

    @Override
    public UsersServiceModel findByUsername(String username) {
        Users userEntity = this.userRepository.findOneByUsername(username);
        return this.modelMapper.map(userEntity, UsersServiceModel.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = this.userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Wrong");
        }

        return user;
    }

    @Override
    public boolean hasAdminRights(Authentication authentication) {
        List<String> authoritiesList = new ArrayList<String>();
        authentication.getAuthorities().forEach(auth -> {
            authoritiesList.add(auth.getAuthority());
        });

        return authoritiesList.contains("ADMIN");
    }

    @Override
    public boolean hasDoctorRights(Authentication authentication) {
        List<String> authoritiesList = new ArrayList<String>();
        authentication.getAuthorities().forEach(auth -> {
            authoritiesList.add(auth.getAuthority());
        });

        return authoritiesList.contains("DOCTOR");
    }

    @Override
    public boolean hasPatientRights(Authentication authentication) {
        List<String> authoritiesList = new ArrayList<String>();
        authentication.getAuthorities().forEach(auth -> {
            authoritiesList.add(auth.getAuthority());
        });

        return authoritiesList.contains("PATIENT");
    }

    @Override
    public List<UserManageViewModel> getAllUsersToManage() {

        List<UserManageViewModel> userManageViewModels = new ArrayList<>();

        this.userRepository.findAll().forEach(user -> {
            UserManageViewModel userManageViewModel = this.modelMapper.map(user, UserManageViewModel.class);
            userManageViewModel.setWard(user.getWard() != null ? user.getWard().getWardName() : "");

            user.getAuthorities().forEach(role -> {
                if(role.getAuthority().equals("PATIENT")){
                    userManageViewModel.setRole("patient");
                }else if(role.getAuthority().equals("DOCTOR")){
                    userManageViewModel.setRole("doctor");
                }else if(role.getAuthority().equals("ADMIN")){
                    userManageViewModel.setRole("admin");
                }else {
                    userManageViewModel.setRole("user");
                }
            });

            userManageViewModels.add(userManageViewModel);
        });

        return userManageViewModels;
    }

    @Override
    public UserManageViewModel manageByUsername(String username) {
        Users userEntity = this.userRepository.findOneByUsername(username);
        UserManageViewModel userManageViewModel = null;

        if(userEntity != null){
            userManageViewModel = this.modelMapper.map(userEntity, UserManageViewModel.class);
        }

        return userManageViewModel;
    }

    @Override
    public void lockUser(String username) {
        Users userEntity = this.userRepository.findOneByUsername(username);

        if(userEntity != null){
            userEntity.setAccountNonLocked(false);
            this.userRepository.save(userEntity);
        }
    }

    @Override
    public void unlockUser(String username) {
        Users userEntity = this.userRepository.findOneByUsername(username);

        if(userEntity != null){
            userEntity.setAccountNonLocked(true);
            this.userRepository.save(userEntity);
        }
    }

    @Override
    public long counterAllUsers() {
        return this.userRepository.count();
    }

    @Override
    public UserMedicalRecordViewModel getAllResultsByUsername(String username) {
        Users userEntity = this.userRepository.findOneByUsername(username);
        UserMedicalRecordViewModel userMedicalRecordViewModel = new UserMedicalRecordViewModel();
        userMedicalRecordViewModel.setEgn(userEntity.getEgn());
        userMedicalRecordViewModel.setFirstName(userEntity.getFirstName());
        userMedicalRecordViewModel.setLastName(userEntity.getLastName());
        userMedicalRecordViewModel.setUsername(userEntity.getUsername());
        Set<ResultBloodViewModel> bloodResults = new HashSet<>();
        Set<ResultIrmViewModel> irmResults = new HashSet<>();
        resultIrmService.findAllByPatient(userEntity).forEach(resultIrmServiceModel -> {
            ResultIrmViewModel resultIrmViewModel = this.modelMapper.map(resultIrmServiceModel, ResultIrmViewModel.class);
            irmResults.add(resultIrmViewModel);
        });

        this.resultBloodService.findAllByPatient(userEntity).forEach(resultBloodServiceModel -> {
            ResultBloodViewModel resultBloodViewModel = this.modelMapper.map(resultBloodServiceModel, ResultBloodViewModel.class);
            bloodResults.add(resultBloodViewModel);
        });

        userMedicalRecordViewModel.setBloodResults(bloodResults);
        userMedicalRecordViewModel.setIrmResults(irmResults);
        return userMedicalRecordViewModel;
    }
}
