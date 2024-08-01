package com.example.androidsqlite;

import java.util.List;

public class Contact {
    private Integer id;
    private String name;
    private String cell;

    // Constructor
    public Contact(Integer id, String name, String cell) {
        this.name = name;
        this.cell = cell;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    public String getCell() {
        return cell;
    }

    public Integer getId() {
        return id;
    }
    // Method to return a string array from a list of Student objects
    public static String[] getContacts(List<Contact> contacts) {
        String[] names = new String[contacts.size()];
        for (int i = 0; i < contacts.size(); i++) {
            names[i] = contacts.get(i).getName();
        }
        return names;
    }
}
