package com.MysticSanta.Domain;

import java.util.Objects;

public class Member {

    String wants = "";
    String notWants = "";
    String ip = "";
    User user;

    public Member(String wants, String notWants, String ip, User user) {
        this.wants = wants;
        this.notWants = notWants;
        this.ip = ip;
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

    public String getIp() {
        return ip;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(ip, member.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }
}
