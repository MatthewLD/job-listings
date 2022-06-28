package com.joblister.joblisterapp.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Api {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;

    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(nullable = false, unique = true)
    private UUID keyvalue;

    public Long getId() {
        return id;
    }

    public UUID getKeyvalue() {
        return keyvalue;
    }

    public void setKeyvalue(UUID keyvalue) {
        this.keyvalue = keyvalue;
    }

    @Override
    public String toString(){
        return "Id: "+id+"\n"+"API-key: "+ keyvalue;
    }
}
