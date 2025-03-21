package com.lab.Assignment03.Entity;

import jakarta.persistence.*;
import org.apache.catalina.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "createAt")
    private LocalDateTime createAt;
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;

    public Role() {
    }

    public Role(String name, LocalDateTime createAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.name = name;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
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

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", name='" + name + '\'' + ", createAt=" + createAt + ", updatedAt=" + updatedAt + ", deletedAt=" + deletedAt + '}';
    }
}
