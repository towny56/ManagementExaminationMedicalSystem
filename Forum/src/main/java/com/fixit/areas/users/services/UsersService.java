package com.fixit.areas.users.services;

import com.fixit.areas.role.entities.Role;
import com.fixit.areas.users.models.service.UsersServiceModel;
import com.fixit.areas.users.models.view.UserManageViewModel;
import com.fixit.areas.users.models.view.UserMedicalRecordViewModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UsersService extends UserDetailsService {
    void createUser(UsersServiceModel userServiceModel);

    boolean isUsernameTaken(String username);

    boolean isEmailTaken(String email);

    UsersServiceModel findByUsername(String username);

    boolean hasAdminRights(Authentication authentication);

    boolean hasDoctorRights(Authentication authentication);

    boolean hasPatientRights(Authentication authentication);

    List<UserManageViewModel> getAllUsersToManage();

    UserManageViewModel manageByUsername(String username);

    void lockUser(String username);

    void unlockUser(String username);

    long counterAllUsers();

    UserMedicalRecordViewModel getAllResultsByUsername(String username);
}
