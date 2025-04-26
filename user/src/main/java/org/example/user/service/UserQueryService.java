package org.example.user.service;

import org.example.core.JPA.repositories.SysUserRepository;
import org.example.core.Response;
import org.example.core.JPA.entities.SysUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

//用户信息查询服务类
@Service
public class UserQueryService {

    private final SysUserRepository sysUserRepository;

    // 定义业务码常量
    private static final String USER_ID_INVALID = "USER_ID_INVALID";
    private static final String INCOMPLETE_PARAMS = "INCOMPLETE_PARAMETERS";
    private static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    private static final String SUCCESS = "SUCCESS";
    private static final String SERVER_ERROR = "SERVER_ERROR";

    public UserQueryService(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    @Transactional(readOnly = true)
    public Response getUserById(String userIdStr) {
        try {
            int userId;
            try {
                userId = Integer.parseInt(userIdStr);
            }catch (NumberFormatException e) {
                return new Response(400, USER_ID_INVALID, "用户ID格式错误");
            }

            if (userId == 0) {
                return new Response(400, INCOMPLETE_PARAMS, "参数不完整");
            }

            SysUser user = sysUserRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));

            return new Response(200, SUCCESS, "操作成功", Map.of(
                    "userId", user.getUserId(),
                    "userName", user.getUserAccount(),
                    "nickName", user.getNickName(),
                    "email", user.getEmail(),
                    "phoneNumber", user.getPhoneNumber(),
                    "sex", user.getSex(),
                    "avatar", user.getAvatarAPI() + "&v=" + user.getVersion()
            ));

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