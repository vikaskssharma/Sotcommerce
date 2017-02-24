package com.sot.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sot.ecommerce.entities.Role;



public interface RoleRepository extends JpaRepository<Role, Integer>
{

}
