package com.example.ndpsh.testrealm.models;

import com.example.ndpsh.testrealm.application.MyAplication;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Dog extends RealmObject {

    @PrimaryKey
    private int Id;
    private String Name;

    public Dog() {} // Only for Realm

    public Dog(String name) {
        Id = MyAplication.DogID.incrementAndGet();
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
