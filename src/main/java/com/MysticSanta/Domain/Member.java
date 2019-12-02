package com.MysticSanta.Domain;

public class Member {

    String FullName = "";
    String key = "";

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    @Override
    public String toString() {
        return "Member{" +
                "FullName='" + FullName + '\'' +
                ", key='" + key + '\'' +
                '}';
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Member(String fullName, String key) {
        FullName = fullName;
        this.key = key;
    }
}
