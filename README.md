# 🚲 校园交通"革命"——人机协同式非机动车智能管理系统

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
![Java](https://img.shields.io/badge/Java-21-red.svg)

> 面向高校场景的非机动车全生命周期管理系统，包含用户服务、考试系统、充电管理、违法举报等核心模块

## 📦 项目结构
```bash
trafficCampus/
├── main/            # 主应用模块
│   ├── src/main/java/org/example/main/
│   │   ├── api/             # API接口层
│   │   ├── Pretreatment/    # 预处理配置
│   │   └── MainApplication.java # 启动类
├── core/            # 核心模块
│   ├── entities/     # JPA实体类
│   ├── repositories/ # 数据仓库接口
│   └── Response.java # 统一响应对象
├── user/            # 用户服务
│   ├── service/     # 用户相关服务
│   └── controllers/ # 用户相关API
├── document/        # 项目文档 
│   ├── trafficcampus.sql    # 数据库初始化脚本
│   ├── API错误码规范.docx    # 错误代码定义
│   ├── 后端模块API.docx     # API接口文档
│   └── 数据词典.docx        # 数据库设计文档
├── charge/          # 充电管理
│   ├── service/     # 充电桩服务
│   └── jsonDTO/     # 充电数据对象
└── ...             # 其他模块
```

## 🛠️ 技术栈
- **后端框架**: Spring Boot 3.4.3
- **数据持久化**: Spring Data JPA + MySQL
- **统一响应**: 自定义Response对象
- **安全控制**: IP白名单机制


## 🚀 快速开始

### 前置要求
- JDK 21
- MySQL 8.0+
- Maven 3.9+

### 启动步骤
```bash
# 1. 克隆仓库
git clone https://github.com/ParNal3355/trafficCampus-UserBackend.git

# 2. 初始化数据库
mysql -u root -p < document/trafficcampus.sql

# 3. 构建项目
mvn clean install -pl core
mvn clean install -pl main

# 4. 配置数据库连接（修改core模块配置）
vim core/src/main/resources/application.properties

# 5. 启动应用
cd main
mvn spring-boot:run