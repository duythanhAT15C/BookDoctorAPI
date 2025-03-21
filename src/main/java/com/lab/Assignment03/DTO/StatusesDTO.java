package com.lab.Assignment03.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

public class StatusesDTO {
    private int id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private LocalDateTime createAt;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private LocalDateTime updateAt;
    @JsonInclude(JsonInclude.Include.NON_NULL) // Loại bỏ khi không phải cơ sở nổi bật
    private LocalDateTime deleteAt;

    public StatusesDTO() {
    }

    public StatusesDTO(int id, String name) {
        this.id = id;
        this.name = name;
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

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public LocalDateTime getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(LocalDateTime deleteAt) {
        this.deleteAt = deleteAt;
    }

    @Override
    public String toString() {
        return "StatusesDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", deleteAt=" + deleteAt +
                '}';
    }
}
