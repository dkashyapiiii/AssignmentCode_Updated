package com.diksha.employeedata.ModelClass;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
//@Entity(tableName="user")
public class Maindata implements Serializable {

//    @PrimaryKey
//    @NonNull
    @SerializedName("firstname")
    String firstname;

    boolean expanded;

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    @SerializedName("lastname")

    String lastname;

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

    @NonNull
    @Expose
    @SerializedName("age")
    String age;
    @NonNull
    @Expose
    @SerializedName("gender")

    String gender;
    @SerializedName("picture")

    String picture;
    @SerializedName("job")

    Jobholder jobholder;

    public Jobholder getJobholder() {
        return jobholder;
    }

    public void setJobholder(Jobholder jobholder) {
        this.jobholder = jobholder;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    @SerializedName("education")

    Education education;
}
