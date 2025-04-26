package org.example.core.JPA.repositories;

import org.example.core.JPA.entities.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户于角色关联表接口
 */

public interface SysUserRoleRepository extends JpaRepository<SysUserRole, SysUserRole.SysUserRolePK> {

}
