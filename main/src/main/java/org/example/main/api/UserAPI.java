package org.example.main.api;

import org.example.core.Response;
import org.example.user.service.*;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user")
public class UserAPI {

    private final UserQueryService userQueryService;
    private final UserUpdateService userUpdateService;
    private final UserDeactivateService userDeactivateService;
    private final UserRegistrationService userRegistrationService;
    private final AvatarStorageService avatarStorageService;
    private final AvatarRetrievalService avatarRetrievalService;
    private final PasswordUpdateService passwordUpdateService;
    private final PasswordChangeService passwordChangeService;
    private final UserAuthService userAuthService;

    public UserAPI(UserQueryService userQueryService,
                   UserUpdateService userUpdateService,
                   UserDeactivateService userDeactivateService,
                   UserRegistrationService userRegistrationService,
                   AvatarStorageService avatarStorageService,
                   AvatarRetrievalService avatarRetrievalService,
                   PasswordUpdateService passwordUpdateService,
                   PasswordChangeService passwordChangeService,
                   UserAuthService userAuthService) {
        this.userQueryService = userQueryService;
        this.userUpdateService = userUpdateService;
        this.userDeactivateService = userDeactivateService;
        this.userRegistrationService = userRegistrationService;
        this.avatarStorageService = avatarStorageService;
        this.avatarRetrievalService = avatarRetrievalService;
        this.passwordUpdateService = passwordUpdateService;
        this.passwordChangeService = passwordChangeService;
        this.userAuthService = userAuthService;
    }

    // 1. 获取用户信息
    @GetMapping("/info/{userId}")
    public Response getUserInfo(@PathVariable String userId) {
        return userQueryService.getUserById(userId);
    }

    // 2. 更新用户信息
    @PutMapping("/update")
    public Response updateUserInfo(
            @RequestParam String userId,
            @RequestParam(required = false) String nickName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String sex) {
        return userUpdateService.updateUserInfo(userId, nickName, email, phoneNumber, sex);
    }

    // 3. 注销用户
    @DeleteMapping("/delete/{userId}")
    public Response deleteUser(@PathVariable String userId) {
        return userDeactivateService.deactivateUser(userId);
    }

    // 4. 注册用户
    @PostMapping("/register")
    public Response registerUser(@RequestParam String phoneNumber,
                                 @RequestParam String nickName,
                                 @RequestParam String email,
                                 @RequestParam String sex,
                                 @RequestParam String password) {
        return userRegistrationService.registerUser(nickName, email, phoneNumber, sex, password);
    }

    // 5. 更新用户头像
    @PutMapping("/avatar/{userId}")
    public Response uploadAvatar(@PathVariable String userId,
                                 @RequestParam("file") MultipartFile file) {
        return avatarStorageService.storeAvatar(userId, file);
    }

    // 6. 获取用户头像
    @GetMapping(value = "/avatar", produces = "image/webp")
    public ResponseEntity<Resource> getAvatar(@RequestParam("id") String userId,
                                              @RequestParam(value = "v", defaultValue = "0") String version) {
        return avatarRetrievalService.getAvatar(userId, version);
    }

    // 7. 忘记密码
    @PostMapping("/forgot")
    public Response forgotPassword(@RequestParam String userName,
                                   @RequestParam String phoneNumber,
                                   @RequestParam String password) {
        return passwordUpdateService.updatePassword(userName, phoneNumber, password);
    }

    // 8. 修改密码
    @PostMapping("/change")
    public Response changePassword(@RequestParam String userId,
                                   @RequestParam String Password,
                                   @RequestParam String newPassword) {
        return passwordChangeService.changePassword(userId, Password, newPassword);
    }

    // 9. 用户登录
    @PostMapping("/login")
    public Response userLogin(@RequestParam String account,
                              @RequestParam String password) {
        return userAuthService.login(account, password);
    }
}