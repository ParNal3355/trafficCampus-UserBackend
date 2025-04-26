package org.example.main.Pretreatment;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.List;

//IP验证拦截器
public class IpAuthInterceptor implements HandlerInterceptor {

    // 允许的反向代理IP列表（配置在application.properties）
    private final List<String> allowedIps;

    public IpAuthInterceptor(List<String> allowedIps) {
        this.allowedIps = allowedIps;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String clientIP = getClientIp(request);

        if (!allowedIps.contains(clientIP)) {
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write("该api不是公共方法，禁止访问!");
            response.setStatus(403);
            return false;
        }
        return true;
    }

    private String getClientIp(HttpServletRequest request) {
        // 获取经过代理的真实IP
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.hasText(ip)) {
            return ip.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}
