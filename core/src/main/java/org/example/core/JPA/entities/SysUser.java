package org.example.core.JPA.entities;

import jakarta.persistence.*;

/**
 * 用户信息实体类
 */

@Entity
@Table(name = "sys_user")
public class SysUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;//用户id

    @Column(name = "user_name", nullable = false)
    private String UserAccount;//用户账号 纯数字

    @Column(name = "nick_name", nullable = false)
    private String nickName;//用户昵称

    @Column(name = "email")
    private String email;//邮箱

    @Column(name = "phonenumber")
    private String phoneNumber;//手机号

    @Column(name = "sex", columnDefinition = "char default '2'")
    private String sex;//性别 0-男 1-女 2-未知

    @Column(name = "avatar")
    private String avatarAPI;//头像请求API

    @Column(name = "password")
    private String password;//密码

    @Column(name = "version",columnDefinition = "int default '0'")
    private Integer version;//版本号 针对于头像版本使用

    @Column(name = "status", columnDefinition = "char default '0'")
    private String status;//用户状态 0-正常 1-停用

    // 无参构造方法
    public SysUser() {
    }

    // 有参构造方法
    public SysUser(String UserAccount, String nickName, String email, String phoneNumber, String avatarAPI, String password) {
        this.UserAccount = UserAccount;
        this.nickName = nickName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.avatarAPI = avatarAPI;
        this.password = password;
    }

    // Getter 和 Setter 方法
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserAccount() {
        return Integer.valueOf(UserAccount);
    }

    public void setUserAccount(Integer userAccount) {
        this.UserAccount = userAccount.toString();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getSex() {
        return Integer.valueOf(sex);
    }

    public void setSex(Integer sex) {
        this.sex = sex.toString();
    }

    public String getAvatarAPI() {
        return avatarAPI;
    }

    public void setAvatarAPI(String avatarAPI) {
        this.avatarAPI = avatarAPI;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getStatus() {
        return Integer.valueOf(status);
    }

    public void setStatus(Integer status) {
        this.status = status.toString();
    }
}