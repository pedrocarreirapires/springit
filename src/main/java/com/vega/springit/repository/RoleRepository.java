package com.vega.springit.repository;

import com.vega.springit.domain.Role;
import com.vega.springit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {


    Role findByName(String name);




}
