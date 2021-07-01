package com.diksha.employeedata.ModelClass;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Jobholder implements Serializable {
    @SerializedName("role")
    String role;
    @SerializedName("exp")
    String exp;
    @SerializedName("organization")
    String organization;

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
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
