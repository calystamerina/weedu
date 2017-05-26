package com.telkom.weedu.data.api.model.edumail;

import java.io.Serializable;

/**
 * Created by sidiqpermana on 4/30/17.
 */

public class Person implements Serializable {
    private String name;
    private String email;

    public Person(String n, String e) { name = n; email = e; }

    public String getName() { return name; }
    public String getEmail() { return email; }

    @Override
    public String toString() { return name; }
}

