package org.example.user.service;

import org.example.core.Response;
import org.example.core.JPA.entities.SysUser;
import org.example.core.JPA.repositories.SysUserRepository;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;

// 用户登录服务类
@Service
public class UserAuthService {

    private final SysUserRepository sysUserRepository;

    // 错误码常量
    private static final String INCOMPLETE_PARAMS = "INCOMPLETE_PARAMETERS";
    private static final String AUTH_FAILED = "AUTH_FAILED";
    private static final String ACCOUNT_DEACTIVATE = "ACCOUNT_DEACTIVATE";
    private static final String SUCCESS = "SUCCESS";
    private static final String INVALID_ACCOUNT = "INVALID_ACCOUNT_FORMAT";

    public UserAuthService(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    public Response login(String account, String password) {
        try {
            // 参数非空检查
            if (account == null || password == null) {
                return new Response(400, INCOMPLETE_PARAMS, "账号密码必须填写");
            }

            // 账号格式验证
            int accountNumber;
            try {
                accountNumber = Integer.parseInt(account);
            } catch (NumberFormatException e) {
                return new Response(400, INVALID_ACCOUNT, "账号格式错误");
            }

            // 查询用户信息
            Optional<SysUser> userOptional = sysUserRepository.findByUserAccount(accountNumber);
            if (userOptional.isEmpty() || !userOptional.get().getPassword().equals(password)) {
                return new Response(401, AUTH_FAILED, "账号或密码错误");
            }

            SysUser user = userOptional.get();

            // 账户状态检查
            if (user.getStatus() == 1) {
                return new Response(403, ACCOUNT_DEACTIVATE, "账户已停用");
            }

            // 构建响应数据
            return new Response(200, SUCCESS, "登录成功", Map.of(
                    "userId", user.getUserId(),
                    "userName", user.getUserAccount(),
                    "nickName", user.getNickName(),
                    "email", user.getEmail(),
                    "phoneNumber", user.getPhoneNumber(),
                    "sex", user.getSex(),
                    "avatar", user.getAvatarAPI() + "&v=" + user.getVersion()
            ));

        } catch (Exception e) {
            return new Response(500, "SERVER_ERROR", "系统内部错误");
        }
    }
}