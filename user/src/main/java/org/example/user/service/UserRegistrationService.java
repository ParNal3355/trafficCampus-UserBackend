package org.example.user.service;

import org.example.core.JPA.repositories.SysUserRepository;
import org.example.core.Response;
import org.example.core.JPA.entities.SysUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

// 用户注册服务类
@Service
public class UserRegistrationService {

    private final SysUserRepository sysUserRepository;

    // 错误码常量
    private static final String INCOMPLETE_PARAMS = "INCOMPLETE_PARAMETERS";
    private static final String DUPLICATE_EMAIL = "DUPLICATE_EMAIL";
    private static final String INVALID_PHONE = "INVALID_PHONE";
    private static final String INVALID_SEX = "INVALID_SEX";
    private static final String SUCCESS = "SUCCESS";
    private static final String SERVER_ERROR = "SERVER_ERROR";

    public UserRegistrationService(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    @Transactional
    public Response registerUser(String nickName, String email,
                                 String phoneNumber, String sex, String password) {
        try {
            // 非空检查
            if (nickName == null || email == null || phoneNumber == null
                    || sex == null || password == null) {
                return new Response(400, INCOMPLETE_PARAMS, "所有参数都必须填写");
            }

            // 邮箱唯一性检查
            if (sysUserRepository.existsByEmail(email)) {
                return new Response(409, DUPLICATE_EMAIL, "邮箱已被注册");
            }

            // 手机号格式验证（简单验证1开头11位数字）
            if (!phoneNumber.matches("^1[3-9]\\d{9}$")) {
                return new Response(400, INVALID_PHONE, "手机号格式不正确");
            }

            // 性别验证
            if (!sex.matches("[0-2]")) {
                return new Response(400, INVALID_SEX, "性别参数无效");
            }

            // 创建新用户
            SysUser newUser = new SysUser();
            newUser.setNickName(nickName);
            newUser.setEmail(email);
            newUser.setPhoneNumber(phoneNumber);
            newUser.setSex(Integer.parseInt(sex));
            newUser.setPassword(password);
            newUser.setStatus(0); // 默认状态0
            newUser.setVersion(0);

            // 首次保存以获取自增ID
            SysUser savedUser = sysUserRepository.save(newUser);

            // 生成用户账号（10000000 + userId）
            int generatedAccount = 10000000 + savedUser.getUserId();
            savedUser.setUserAccount(generatedAccount);

            // 设置头像API地址
            savedUser.setAvatarAPI("/api/user/avatar/id=" + savedUser.getUserId());

            // 更新用户信息
            sysUserRepository.save(savedUser);

            return new Response(200, SUCCESS, "注册成功", Map.of(
                    "userId", savedUser.getUserId(),
                    "userName", generatedAccount,
                    "nickName", savedUser.getNickName(),
                    "email", savedUser.getEmail(),
                    "phoneNumber", savedUser.getPhoneNumber(),
                    "sex", savedUser.getSex(),
                    "avatar", savedUser.getAvatarAPI() + "&v=" + savedUser.getVersion()
            ));

        } catch (Exception e) {
            return new Response(500, SERVER_ERROR, "系统内部错误");
        }
    }
}