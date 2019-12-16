package com.MysticSanta.Domain;

public class Member {

    String wants = "";
    String notWants = "";
    User user;

    public Member(String wants, String notWants, User user) {
        this.wants = wants;
        this.notWants = notWants;
        this.user = user;
    }

    public String getWants() {
        return wants;
    }

    public void setWants(String wants) {
        this.wants = wants;
    }

    public String getNotWants() {
        return notWants;
    }

    public void setNotWants(String notWants) {
        this.notWants = notWants;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
