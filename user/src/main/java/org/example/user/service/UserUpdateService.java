package org.example.user.service;

import org.example.core.JPA.repositories.SysUserRepository;
import org.example.core.Response;
import org.example.core.JPA.entities.SysUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//更新用户信息（头像除外）服务类
@Service
public class UserUpdateService {

    private final SysUserRepository sysUserRepository;

    // 错误码常量
    private static final String USER_ID_INVALID = "USER_ID_INVALID";
    private static final String INCOMPLETE_PARAMS = "INCOMPLETE_PARAMETERS";
    private static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    private static final String DUPLICATE_EMAIL = "DUPLICATE_EMAIL";
    private static final String SUCCESS = "SUCCESS";
    private static final String SERVER_ERROR = "SERVER_ERROR";

    public UserUpdateService(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    @Transactional
    public Response updateUserInfo(String userId, String nickName,
                                      String email, String phoneNumber, String sex) {
        try {
            // 参数校验
            try {
                Integer.parseInt(userId);
            }catch (NumberFormatException e) {
                return new Response(400, USER_ID_INVALID, "用户ID格式错误");
            }

            if (nickName == null && email == null && phoneNumber == null && sex == null) {
                return new Response(400, INCOMPLETE_PARAMS, "至少需要修改一个字段");
            }

            // 用户存在性检查
            SysUser user = sysUserRepository.findById(Integer.parseInt(userId))
                    .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));

            // 邮箱重复性检查
            if (email != null && !email.isEmpty()) {
                if (sysUserRepository.existsByEmailAndUserIdNot(email, user.getUserId())) {
                    return new Response(409, DUPLICATE_EMAIL, "邮箱已被占用");
                }
            }

            // 执行字段更新
            updateFieldIfPresent(nickName, user::setNickName);
            updateFieldIfPresent(email, user::setEmail);
            updateFieldIfPresent(phoneNumber, user::setPhoneNumber);
            updateFieldIfPresent(sex, s -> user.setSex(Integer.parseInt(s)));

            sysUserRepository.save(user);
            return new Response(200, SUCCESS, "更新成功");

        } catch (RuntimeException e) {
            if (e.getMessage().contains(USER_NOT_FOUND)) {
                return new Response(404, USER_NOT_FOUND, "用户不存在");
            }
            return new Response(500, SERVER_ERROR, "系统内部错误");
        } catch (Exception e) {
            return new Response(500, SERVER_ERROR, "系统内部错误");
        }
    }

    private void updateFieldIfPresent(String value, java.util.function.Consumer<String> setter) {
        if (value != null && !value.isEmpty()) {
            setter.accept(value);
        }
    }
}