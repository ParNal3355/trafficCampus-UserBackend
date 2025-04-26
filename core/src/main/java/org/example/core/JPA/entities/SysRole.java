package org.example.core.JPA.entities;

import jakarta.persistence.*;

/**
 * 角色信息实体类
 */

@Entity
@Table(name = "sys_role")
public class SysRole {
    @Id
    @Column(name = "role_id")
    private Integer roleId;// 角色ID

    @Column(name = "role_name", nullable = false)
    private String roleName;// 角色名称

    // 无参构造方法
    public SysRole() {
    }

    // 有参构造方法
    public SysRole(Integer roleId ,String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    // Getter 和 Setter 方法
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}