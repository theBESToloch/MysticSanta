package com.MysticSanta.Domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class BindMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bind_id", nullable = false)
    private String id;

    @OneToOne
    @JoinColumn(name = "member_from_id", referencedColumnName = "member_id")
    private Member memberFrom;

    @OneToOne
    @JoinColumn(name = "member_to_id", referencedColumnName = "member_id")
    private Member memberTo;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Member getMemberFrom() {
        return memberFrom;
    }

    public BindMember setMemberFrom(Member memberFrom) {
        this.memberFrom = memberFrom;
        return this;
    }

    public Member getMemberTo() {
        return memberTo;
    }

    public BindMember setMemberTo(Member memberTo) {
        this.memberTo = memberTo;
        return this;
    }
}
