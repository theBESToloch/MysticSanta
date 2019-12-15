package com.MysticSanta.Domain;

import java.util.*;

public class User {
    String firstName;
    String lastName;
    String id;
    String userId;
    Set<Role> role = new HashSet();

    public static int nextId = 0;

    public User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = String.valueOf(new Random().nextInt(100000));
        this.userId = String.valueOf(nextId++);
        this.role.add(Role.USER);
        if (this.userId.equals("0")) {
            this.role.add(Role.ADMIN);
        }
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }
}
