package org.example.user.service;

import org.example.core.JPA.repositories.SysUserRepository;
import org.example.core.Response;
import org.example.core.JPA.entities.SysUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//用户注销类
@Service
public class UserDeactivateService {

    private final SysUserRepository sysUserRepository;

    // 复用已有错误码常量
    private static final String USER_ID_INVALID = "USER_ID_INVALID";
    private static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    private static final String SUCCESS = "SUCCESS";
    private static final String SERVER_ERROR = "SERVER_ERROR";

    public UserDeactivateService(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    @Transactional
    public Response deactivateUser(String userIdStr) {
        try {
            // ID格式验证
            final int userId;
            try {
                userId = Integer.parseInt(userIdStr);
            } catch (NumberFormatException e) {
                return new Response(400, USER_ID_INVALID, "用户ID格式错误");
            }

            // 用户存在性检查
            SysUser user = sysUserRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));

            // 更新状态为停用（1）
            user.setStatus(1);
            sysUserRepository.save(user);

            return new Response(200, SUCCESS, "用户已注销");

        } catch (RuntimeException e) {
            if (e.getMessage().contains(USER_NOT_FOUND)) {
                return new Response(404, USER_NOT_FOUND, "用户不存在");
            }
            return new Response(500, SERVER_ERROR, "系统内部错误");
        } catch (Exception e) {
            return new Response(500, SERVER_ERROR, "系统内部错误");
        }
    }
}