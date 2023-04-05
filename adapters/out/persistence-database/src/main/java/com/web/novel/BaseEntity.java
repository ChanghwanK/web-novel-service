package com.web.novel;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;

    @PrePersist
    void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        lastModifiedAt = LocalDateTime.now();
    }
}
