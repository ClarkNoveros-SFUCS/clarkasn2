package com.clarkasn2.clarkasn2.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="usersTable")
public class Users {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int uid;

    @NotBlank(message = "Please fill up name")
    @Column (nullable = false)
    private String name;

    @NotBlank(message="Please fill up email")
    @Email(message="Enter valid email")
    @Size(max=150, message="Cannot exceed 150 characters")
    @Column(nullable=false, length=150, unique=true)
    private String email;

    @NotNull (message = "Please select role")
    @Enumerated (EnumType.STRING)
    @Column(nullable=false)
    private Role roleType;

    @Min(value = 1, message="Out of range: between 1 & 10")
    @Max(value = 10, message="Out of range: between 1 & 10")
    @Column(nullable=false)
    private int clarity;

    @Min(value = 1, message="Out of range: between 1 & 10")
    @Max(value = 10, message="Out of range: between 1 & 10")
    @Column(nullable=false)
    private int niceness;

    @Min(value = 1, message="Out of range: between 1 & 10")
    @Max(value = 10, message="Out of range: between 1 & 10")
    @Column(nullable=false)
    private int knowledgeableScore;

    @Size(max=350, message="Cannot exceed 350 characters")
    @Column(length=350)
    private String comment;

    @Column(nullable=false, updatable=false)
    private LocalDateTime createdAt;

    @Column(nullable=false)
    private LocalDateTime modifiedAt;

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        modifiedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        modifiedAt = LocalDateTime.now();
    }

    //Getters and Setters and constructors

    //default constructor
    public Users(){
    }

    //parameterized constructor 
    public Users(int uid, String name, String email, Role roleType, int clarity, int niceness, int knowledgeableScore, String comment){
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.roleType = roleType;
        this.clarity = clarity;
        this.niceness = niceness;
        this.knowledgeableScore = knowledgeableScore;
        this.comment = comment;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRoleType() {
        return roleType;
    }

    public void setRoleType(Role roleType) {
        this.roleType = roleType;
    }

    public int getClarity() {
        return clarity;
    }

    public void setClarity(int clarity) {
        this.clarity = clarity;
    }

    public int getNiceness() {
        return niceness;
    }

    public void setNiceness(int niceness) {
        this.niceness = niceness;
    }

    public int getKnowledgeableScore() {
        return knowledgeableScore;
    }

    public void setKnowledgeableScore(int knowledgeableScore) {
        this.knowledgeableScore = knowledgeableScore;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    //method for the overall score, I will use the average 
    public double getAverageScore(){
        return Math.round(((clarity + niceness + knowledgeableScore ) / 3.0) * 10) / 10.0;
    }

    @Transient
    private StaffMemberProfile profile;

    public StaffMemberProfile getProfile(){
        return profile;
    }

    public void setProfile(StaffMemberProfile profile){
        this.profile = profile;
    }
}
