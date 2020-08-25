package com.fixit.areas.role.services;

import com.fixit.areas.role.models.service.RoleServiceModel;

public interface RoleService {

    RoleServiceModel findByAuthority(String authority);

    void addRole(RoleServiceModel roleServiceModel);

}
