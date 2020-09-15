package com.example.avatarcontacts;

import java.io.Serializable;

public class Contact implements Serializable {
    String name, email, phone, dept;
    int imgId, id;

    static int currentMax = 0;

    public Contact(String name, String email, String phone, String dept, int imgId) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dept = dept;
        this.imgId = imgId;
        this.id = currentMax;
        currentMax++;
    }
}
