package com.abhishek.bookstore.data.entities;

import java.time.Instant;
import javax.persistence.Column;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
public class AuditableEntity {

    @CreatedDate
    @Column(name = "CREATED_AT")
    protected Instant createdAt;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_AT")
    protected Instant lastModifiedAt;
}
