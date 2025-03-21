package com.lab.Assignment03.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lab.Assignment03.Entity.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class UserDTO {
    private int id;
    private String name;
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private String password;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private String rePassword;
    private String address;

    private String phone;

    private String avatar;

    private String gender;

    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private Integer isActive;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private LocalDateTime createAt;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private LocalDateTime updatedAt;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private LocalDateTime deletedAt;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private Integer roleId;


    public UserDTO() {
    }

    public UserDTO(int id, String name, String email, String password, String rePassword, String address, String phone, String avatar, String gender, String description, Integer isActive, LocalDateTime createAt, LocalDateTime updatedAt, LocalDateTime deletedAt, Integer roleId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.rePassword = rePassword;
        this.address = address;
        this.phone = phone;
        this.avatar = avatar;
        this.gender = gender;
        this.description = description;
        this.isActive = isActive;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.roleId = roleId;
    }

    public UserDTO(String name, String email, String address, String phone, String avatar, String gender, String description) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.avatar = avatar;
        this.gender = gender;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}
