-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: trafficcampus
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `charging_records`
--

DROP TABLE IF EXISTS `charging_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `charging_records` (
  `record_id` int NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `user_id` int DEFAULT NULL COMMENT '用户ID',
  `plate_id` int DEFAULT NULL COMMENT '车牌ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `fee` decimal(10,2) DEFAULT NULL COMMENT '费用',
  `status` varchar(10) DEFAULT NULL COMMENT '付款状态',
  `location_id` int DEFAULT NULL COMMENT '充电地点id',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='充电记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `charging_records`
--

LOCK TABLES `charging_records` WRITE;
/*!40000 ALTER TABLE `charging_records` DISABLE KEYS */;
/*!40000 ALTER TABLE `charging_records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chargingstations`
--

DROP TABLE IF EXISTS `chargingstations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chargingstations` (
  `stationID` int NOT NULL AUTO_INCREMENT,
  `stationName` varchar(50) NOT NULL,
  `location` varchar(255) NOT NULL,
  `installDate` date NOT NULL,
  `feeStandard` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`stationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='充电桩表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chargingstations`
--

LOCK TABLES `chargingstations` WRITE;
/*!40000 ALTER TABLE `chargingstations` DISABLE KEYS */;
/*!40000 ALTER TABLE `chargingstations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exams`
--

DROP TABLE IF EXISTS `exams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exams` (
  `exam_id` int NOT NULL AUTO_INCREMENT COMMENT '选项ID',
  `question_id` int DEFAULT NULL COMMENT '问题ID',
  `exam_content` text COMMENT '选项',
  PRIMARY KEY (`exam_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='选项表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exams`
--

LOCK TABLES `exams` WRITE;
/*!40000 ALTER TABLE `exams` DISABLE KEYS */;
/*!40000 ALTER TABLE `exams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `license_plates`
--

DROP TABLE IF EXISTS `license_plates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `license_plates` (
  `plate_id` int NOT NULL AUTO_INCREMENT COMMENT '车牌id',
  `plate_number` varchar(20) DEFAULT NULL COMMENT '车牌号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `user_id` int DEFAULT NULL COMMENT '用户',
  `vehicle_type` varchar(50) DEFAULT NULL COMMENT '车辆类型',
  `score` int DEFAULT NULL COMMENT '车牌的分数',
  `status` char(1) DEFAULT '0' COMMENT '车牌状态（0正常 1停用）',
  PRIMARY KEY (`plate_id`),
  UNIQUE KEY `plate_number` (`plate_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='车牌表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `license_plates`
--

LOCK TABLES `license_plates` WRITE;
/*!40000 ALTER TABLE `license_plates` DISABLE KEYS */;
/*!40000 ALTER TABLE `license_plates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `location_id` int NOT NULL AUTO_INCREMENT,
  `location` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='充电地点表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions` (
  `question_id` int NOT NULL AUTO_INCREMENT COMMENT '问题ID',
  `question_content` text COMMENT '问题内容',
  `answer` text COMMENT '答案',
  `category` varchar(50) DEFAULT NULL COMMENT '问题类型',
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='题库表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reports` (
  `report_id` int NOT NULL AUTO_INCREMENT COMMENT '举报记录ID',
  `user_id` int DEFAULT NULL COMMENT '举报人ID',
  `plate_id` int DEFAULT NULL COMMENT '违规车牌ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `report_content` text COMMENT '举报内容',
  `status` varchar(20) DEFAULT NULL COMMENT '处理状态',
  `score` int DEFAULT NULL COMMENT '扣分分值',
  `plate_number` varchar(20) DEFAULT NULL COMMENT '车牌号',
  PRIMARY KEY (`report_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='举报信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `score` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `score` int NOT NULL,
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='分数表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `version` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户和角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volunteer_positions`
--

DROP TABLE IF EXISTS `volunteer_positions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `volunteer_positions` (
  `volunteer_position_id` int NOT NULL AUTO_INCREMENT COMMENT '志愿岗位ID',
  `position_type` varchar(100) NOT NULL COMMENT '岗位类型',
  `volunteer_location` varchar(200) NOT NULL COMMENT '志愿地点',
  `job_content` text COMMENT '工作内容',
  `deadline` datetime DEFAULT NULL COMMENT '截止时间',
  `current_number` int DEFAULT NULL COMMENT '当前人数',
  `required_number` int DEFAULT NULL COMMENT '所需人数',
  `position_status` varchar(50) NOT NULL COMMENT '岗位状态',
  PRIMARY KEY (`volunteer_position_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='志愿岗位表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volunteer_positions`
--

LOCK TABLES `volunteer_positions` WRITE;
/*!40000 ALTER TABLE `volunteer_positions` DISABLE KEYS */;
/*!40000 ALTER TABLE `volunteer_positions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volunteer_services`
--

DROP TABLE IF EXISTS `volunteer_services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `volunteer_services` (
  `service_id` int NOT NULL AUTO_INCREMENT COMMENT '志愿ID',
  `user_id` int DEFAULT NULL COMMENT '志愿者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `service_location` varchar(100) DEFAULT NULL COMMENT '志愿地点',
  `description` text COMMENT '描述',
  `plate_id` int DEFAULT NULL COMMENT '车牌号id',
  `score` int DEFAULT NULL COMMENT '加分分数',
  `status` varchar(10) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='志愿信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volunteer_services`
--

LOCK TABLES `volunteer_services` WRITE;
/*!40000 ALTER TABLE `volunteer_services` DISABLE KEYS */;
/*!40000 ALTER TABLE `volunteer_services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wrong_question`
--

DROP TABLE IF EXISTS `wrong_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wrong_question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL COMMENT '用户ID',
  `question_id` int NOT NULL COMMENT '题目ID',
  `selected_option` int NOT NULL COMMENT '用户错误选项ID',
  `source` varchar(20) NOT NULL COMMENT '来源practice/exam/simulation',
  PRIMARY KEY (`id`),
  KEY `idx_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户错题记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wrong_question`
--

LOCK TABLES `wrong_question` WRITE;
/*!40000 ALTER TABLE `wrong_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `wrong_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'trafficcampus'
--

--
-- Dumping routines for database 'trafficcampus'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-24 17:57:06
