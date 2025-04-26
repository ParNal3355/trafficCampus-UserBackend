package org.example.user.service;

import org.example.core.Response;
import org.example.core.JPA.entities.SysUser;
import org.example.core.JPA.repositories.SysUserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

// 修改密码服务类
@Service
public class PasswordChangeService {

    private final SysUserRepository sysUserRepository;

    // 错误码常量
    private static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    private static final String OLD_PASSWORD_WRONG = "OLD_PASSWORD_WRONG";
    private static final String NEW_PASSWORD_SAME = "NEW_PASSWORD_SAME";
    private static final String SUCCESS = "SUCCESS";
    private static final String SERVER_ERROR = "SERVER_ERROR";

    public PasswordChangeService(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    public Response changePassword(String userIdStr, String oldPassword, String newPassword) {
        try {
            // 参数验证
            int userId = Integer.parseInt(userIdStr);

            // 用户存在性检查
            Optional<SysUser> userOptional = sysUserRepository.findById(userId);
            if (userOptional.isEmpty()) {
                return new Response(404, USER_NOT_FOUND, "用户不存在");
            }

            SysUser user = userOptional.get();

            // 旧密码验证
            if (!user.getPassword().equals(oldPassword)) {
                return new Response(401, OLD_PASSWORD_WRONG, "原密码错误");
            }

            // 新旧密码对比
            if (oldPassword.equals(newPassword)) {
                return new Response(400, NEW_PASSWORD_SAME, "新密码不能与旧密码相同");
            }

            // 更新密码
            user.setPassword(newPassword);
            sysUserRepository.save(user);

            return new Response(200, SUCCESS, "密码修改成功");

        } catch (NumberFormatException e) {
            return new Response(400, "INVALID_USER_ID", "用户ID格式错误");
        } catch (Exception e) {
            return new Response(500, SERVER_ERROR, "系统内部错误");
        }
    }
}