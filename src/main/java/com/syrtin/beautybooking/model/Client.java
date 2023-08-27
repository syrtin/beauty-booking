package com.syrtin.beautybooking.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table("clients")
public class Client {
    @Id
    private Long id;
    private String name;
    private String phone;

    public Client(String name, String phone) {
        this(null, name, phone);
    }
}
