package org.example.user.service;

import org.example.core.Response;
import org.example.core.JPA.entities.SysUser;
import org.example.core.JPA.repositories.SysUserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

//忘记密码的服务类
@Service
public class PasswordUpdateService {

    private final SysUserRepository sysUserRepository;

    // 错误码常量
    private static final String INCOMPLETE_PARAMS = "INCOMPLETE_PARAMETERS";
    private static final String INVALID_USERNAME = "INVALID_USERNAME";
    private static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    private static final String PHONE_ERROR = "PHONE_ERROR";
    private static final String SUCCESS = "SUCCESS";
    private static final String SERVER_ERROR = "SERVER_ERROR";

    public PasswordUpdateService(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    public Response updatePassword(String userName, String phoneNumber, String newPassword) {
        try {
            // 非空检查
            if (userName == null || phoneNumber == null || newPassword == null) {
                return new Response(400, INCOMPLETE_PARAMS, "所有参数都必须填写");
            }

            // 用户名数字校验
            int accountNumber;
            try {
                accountNumber = Integer.parseInt(userName);
            } catch (NumberFormatException e) {
                return new Response(400, INVALID_USERNAME, "用户账号格式错误");
            }

            // 用户存在性检查
            Optional<SysUser> userOptional = sysUserRepository.findByUserAccount(accountNumber);
            if (userOptional.isEmpty()) {
                return new Response(404, USER_NOT_FOUND, "用户不存在");
            }

            // 手机号匹配验证
            SysUser user = userOptional.get();
            if (!user.getPhoneNumber().equals(phoneNumber)) {
                return new Response(404, PHONE_ERROR, "手机号错误");
            }

            // 更新密码
            user.setPassword(newPassword);
            sysUserRepository.save(user);

            return new Response(200, SUCCESS, "密码更新成功");

        } catch (Exception e) {
            return new Response(500, SERVER_ERROR, "系统内部错误");
        }
    }
}