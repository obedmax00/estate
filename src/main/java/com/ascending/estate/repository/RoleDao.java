package com.ascending.estate.repository;

import com.ascending.estate.model.Role;

public interface RoleDao {
    Role getRoleByName(String name);
}
