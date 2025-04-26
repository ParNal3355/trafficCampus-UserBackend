package org.example.core.JPA.repositories;

import org.example.core.JPA.entities.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 角色信息接口
 */

public interface SysRoleRepository extends JpaRepository<SysRole, Integer> {

}