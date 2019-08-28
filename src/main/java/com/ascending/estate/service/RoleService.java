package com.ascending.estate.service;

import com.ascending.estate.model.Role;
import com.ascending.estate.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }
}
