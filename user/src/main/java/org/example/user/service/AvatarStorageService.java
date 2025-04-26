package org.example.user.service;

import net.coobird.thumbnailator.Thumbnails;
import org.example.core.Response;
import org.example.core.JPA.entities.SysUser;
import org.example.core.JPA.repositories.SysUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

//更新用户头像服务类
@Service
public class AvatarStorageService {

    private final SysUserRepository sysUserRepository;

    // 错误码常量
    private static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    private static final String VERSION_INVALID = "VERSION_INVALID";
    private static final String INVALID_IMAGE_FORMAT = "INVALID_IMAGE_FORMAT";
    private static final String FILE_SAVE_ERROR = "FILE_SAVE_ERROR";
    private static final String SUCCESS = "SUCCESS";
    private static final String SERVER_ERROR = "SERVER_ERROR";

    private static final double QUALITY_FACTOR = 0.8;
    private static final double MIN_QUALITY = 0.5;
    private static final int MAX_IMAGE_WIDTH = 1024;
    public AvatarStorageService(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    public Response storeAvatar(String userIdStr, MultipartFile file) {
        try {
            // 参数验证
            int userId = Integer.parseInt(userIdStr);

            // 查找用户
            Optional<SysUser> userOptional = sysUserRepository.findById(userId);
            if (userOptional.isEmpty()) {
                return new Response(404, USER_NOT_FOUND, "用户不存在");
            }

            // 验证文件类型
            if (!isValidImage(file)) {
                return new Response(400, INVALID_IMAGE_FORMAT, "仅支持图片格式");
            }

            // 创建存储目录
            Path userDir = Paths.get("./trafficCampus/users/" + userId);
            Files.createDirectories(userDir);

            // 压缩并保存为webp格式
            Path outputPath = userDir.resolve("avatar.webp");

            // 读取原始图片
            BufferedImage originalImage = ImageIO.read(file.getInputStream());

            // 动态质量计算
            double actualQuality = Math.max(
                    QUALITY_FACTOR - (originalImage.getWidth() / 2000.0),
                    MIN_QUALITY
            );

            // 构建压缩参数
            Thumbnails.Builder<BufferedImage> builder = Thumbnails.of(originalImage)
                    .outputFormat("webp")
                    .outputQuality(actualQuality);

            if (originalImage.getWidth() > MAX_IMAGE_WIDTH) {
                builder.width(MAX_IMAGE_WIDTH).keepAspectRatio(true);
            } else {
                builder.scale(1.0);
            }

            // 保存文件
            builder.toFile(outputPath.toFile());

            // 验证文件完整性后更新版本号
            SysUser user = userOptional.get();
            user.setVersion(user.getVersion() + 1);  // 版本号+1
            sysUserRepository.save(user);  // 保存到数据库

            return new Response(200, SUCCESS, "头像更新成功");

        } catch (NumberFormatException e) {
            return new Response(400, VERSION_INVALID, "版本号格式错误");
        } catch (IOException e) {
            return new Response(500, FILE_SAVE_ERROR, "文件存储失败");
        } catch (Exception e) {
            return new Response(500, SERVER_ERROR, "系统内部错误");
        }
    }

    private boolean isValidImage(MultipartFile file) throws IOException {
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            return image != null && file.getContentType().startsWith("image/");
        } catch (IOException e) {
            return false;
        }
    }

    private boolean isValidWebp(Path filePath) {
        try {
            BufferedImage image = ImageIO.read(filePath.toFile());
            return image != null && image.getWidth() > 0;
        } catch (IOException e) {
            return false;
        }
    }
}