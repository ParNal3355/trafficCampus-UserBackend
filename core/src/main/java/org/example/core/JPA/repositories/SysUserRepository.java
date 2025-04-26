package org.example.core.JPA.repositories;

import org.example.core.JPA.entities.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * 用户信息接口
 */

public interface SysUserRepository extends JpaRepository<SysUser, Integer> {

    //判断邮箱是否存在
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM SysUser u WHERE u.email = ?1 AND u.userId <> ?2")
    boolean existsByEmailAndUserIdNot(String email, Integer userId);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM SysUser u WHERE u.UserAccount = :userAccount")
    Optional<SysUser> findByUserAccount(@Param("userAccount") Integer userAccount);


}
