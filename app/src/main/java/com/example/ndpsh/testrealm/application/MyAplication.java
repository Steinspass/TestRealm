package com.example.ndpsh.testrealm.application;

import android.app.Application;


import com.example.ndpsh.testrealm.models.Dog;
import com.example.ndpsh.testrealm.models.Person;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MyAplication extends Application {
    public static AtomicInteger PersonID = new AtomicInteger();
    public static  AtomicInteger DogID = new AtomicInteger();


    @Override
    public void onCreate() {
        super.onCreate();
        setUpRealmConfig();

        Realm realm = Realm.getDefaultInstance();
        PersonID = getIdByTable(realm, Person.class);
        DogID = getIdByTable(realm, Dog.class);

        realm.close();
    }

    private void setUpRealmConfig() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass) {
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size()>0) ? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();
    }
}