package com.diksha.employeedata.ModelClass;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RoomModel {

    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "first_name")
    String firstname;
    @ColumnInfo(name = "last_name")
    String lastname;
    boolean expanded;

    public RoomModel(String firstname, String lastname, String age, String gender, String picture,
                     String exp, String org, String role, String degree, String institute) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.picture = picture;
        this.exp = exp;
        this.org = org;
        this.role = role;
        this.degree = degree;
        this.institute = institute;
    }

    public RoomModel(String firstname, String lastname, String age, String gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public RoomModel() {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;

    }
    @ColumnInfo(name = "age")

    String age;
    @ColumnInfo(name = "gender")

    String gender;

    @ColumnInfo(name = "picture")

    String picture;

    @ColumnInfo(name = "exp")

    String exp;

    @ColumnInfo(name = "org")

    String org;
    @ColumnInfo(name = "role")

    String role;
    @ColumnInfo(name = "degree")

    String degree;

    @ColumnInfo(name = "institute")
    String institute;


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getDegree() {
        return degree;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }



    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getInstitution() {
        return institute;
    }

    public void setInstitution(String institution) {
        this.institute = institution;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getOrganization() {
        return org;
    }

    public void setOrganization(String organization) {
        this.org = organization;
    }


}
