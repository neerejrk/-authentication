package com.authentication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "userflag")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserFlag {
    @Id
    private int id;
    private String type;
    private String value;
}
