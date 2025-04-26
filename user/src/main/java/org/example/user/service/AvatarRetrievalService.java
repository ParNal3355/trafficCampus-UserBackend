package org.example.user.service;

import org.example.core.JPA.repositories.SysUserRepository;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//返回用户头像服务类
@Service
public class AvatarRetrievalService {

    private static final String DEFAULT_AVATAR_PATH = "./trafficCampus/users/avatar.webp";
    private final SysUserRepository sysUserRepository;

    public AvatarRetrievalService(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    public ResponseEntity<Resource> getAvatar(String userIdStr, String versionStr) {
        try {
            // 验证用户存在性
            int userId = Integer.parseInt(userIdStr);
            if (!sysUserRepository.existsById(userId)) {
                return ResponseEntity.notFound().build();
            }

            // 验证版本号格式
            int version = Integer.parseInt(versionStr);

            Path avatarPath;
            if (version == 0) {
                avatarPath = Paths.get(DEFAULT_AVATAR_PATH);
            } else {
                // 优先尝试用户专属头像
                Path userAvatarPath = Paths.get("./trafficCampus/users/" + userId + "/avatar.webp");
                avatarPath = Files.exists(userAvatarPath) ? userAvatarPath : Paths.get(DEFAULT_AVATAR_PATH);
            }

            // 返回文件资源
            Resource resource = new FileSystemResource(avatarPath);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "image/webp")
                    .body(resource);

        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}