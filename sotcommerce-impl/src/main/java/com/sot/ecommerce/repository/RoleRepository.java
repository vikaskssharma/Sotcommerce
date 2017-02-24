package com.sot.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sot.ecommerce.entitities.Role;



public interface RoleRepository extends JpaRepository<Role, Integer>
{

}
