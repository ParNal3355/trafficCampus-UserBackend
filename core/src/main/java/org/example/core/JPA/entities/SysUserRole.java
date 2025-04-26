package org.example.core.JPA.entities;

import jakarta.persistence.*;

/**
 * 用户和角色关联类
 */

@Entity
@Table(name = "sys_user_role")
@IdClass(SysUserRole.SysUserRolePK.class)
public class SysUserRole {
    @Id
    @Column(name = "user_id")
    private Integer userId;//用户id

    @Id
    @Column(name = "role_id")
    private Integer roleId;//角色id

    // 无参构造方法
    public SysUserRole() {
    }

    // 有参构造方法
        public SysUserRole(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    // Getter 和 Setter 方法
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    // 复合主键类
    public static class SysUserRolePK implements java.io.Serializable {
        private Integer userId;
        private Integer roleId;

        public SysUserRolePK() {
        }

        public SysUserRolePK(Integer userId, Integer roleId) {
            this.userId = userId;
            this.roleId = roleId;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getRoleId() {
            return roleId;
        }

        public void setRoleId(Integer roleId) {
            this.roleId = roleId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SysUserRolePK that = (SysUserRolePK) o;
            return userId.equals(that.userId) && roleId.equals(that.roleId);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(userId, roleId);
        }
    }
}
