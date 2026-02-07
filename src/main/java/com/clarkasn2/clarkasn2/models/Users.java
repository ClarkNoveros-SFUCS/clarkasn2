package com.clarkasn2.clarkasn2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="usersTable")
public class Users {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int uid;
}
