package com.lab.Assignment03.Entity;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;

@Entity
@Table(name = "places")
public class Places {
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
    @OneToOne(mappedBy = "places", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private Extrainfos extrainfos;

    public Places() {
    }

    public Places(String name, LocalDateTime createAt, LocalDateTime updatedAt, LocalDateTime deletedAt, Extrainfos extrainfos) {
        this.name = name;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.extrainfos = extrainfos;
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

    public Extrainfos getExtrainfos() {
        return extrainfos;
    }

    public void setExtrainfos(Extrainfos extrainfos) {
        this.extrainfos = extrainfos;
    }
}
