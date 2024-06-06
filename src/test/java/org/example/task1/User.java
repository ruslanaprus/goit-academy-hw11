package org.example.task1;

public class User {
    private int customerId;
    private String name;

    public User(int customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    @Override
    public String toString() {
        return customerId + " - " + name;
    }
}
