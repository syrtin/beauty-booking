package com.syrtin.beautybooking.model;

import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

@Table("client")
public class Client {
    @Id
    private final Long id;

    @NonNull
    private final String name;

    @NonNull
    private final String phone;

    @PersistenceCreator
    public Client(Long id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Client(String name, String phone) {
        this(null, name, phone);
    }

    public Client() {
        this(null, null, null);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
