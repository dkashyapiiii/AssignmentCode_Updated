package com.diksha.employeedata.ModelClass;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Education implements Serializable {
    @SerializedName("degree")

    String degree;
    @SerializedName("institution")

    String institution;

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }
}
