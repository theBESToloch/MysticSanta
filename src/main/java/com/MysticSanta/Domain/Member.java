package com.MysticSanta.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long id;

    @OneToOne(mappedBy = "member")
    private User user;

    private String wants = "";
    private String notWants = "";

    @OneToOne
    private BindMember bindMember;

    public Member() {
    }

    public Member(String wants, String notWants) {
        this.wants = wants;
        this.notWants = notWants;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Member setUser(User user) {
        this.user = user;
        return this;
    }

    public BindMember getBindMember() {
        return bindMember;
    }

    public Member setBindMember(BindMember memberTo) {
        this.bindMember = memberTo;
        return this;
    }
}
