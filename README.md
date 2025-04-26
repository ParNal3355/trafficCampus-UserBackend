# 车辆管理系统项目

## 📋 环境要求
- JDK 21
- Maven 3.9+
- MySQL 8.0+

## 🚀 快速开始
```bash
# 1. 初始化父工程
mvn clean install -N

# 2. 按顺序构建核心模块
mvn clean install -pl core

# 3. 构建其他模块（可选）
mvn clean install -pl module1,module2,module3,module4,module5,module6

# 4. 启动主应用
cd main
mvn spring-boot:run
