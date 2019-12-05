package com.MysticSanta.Domain;

import java.util.Objects;

public class Member {

    String FullName = "";
    String ip = "";

    public Member(String fullName, String ip) {
        FullName = fullName;
        this.ip = ip;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

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
                ", ip='" + ip + '\'' +
                '}';
    }
}
