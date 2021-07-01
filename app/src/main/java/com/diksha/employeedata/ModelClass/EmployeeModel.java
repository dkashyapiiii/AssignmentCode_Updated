package com.diksha.employeedata.ModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel implements Serializable {



    public List<Maindata> getBanner1() {
        return banner1;
    }

    public void setBanner1(List<Maindata> banner1) {
        this.banner1 = banner1;
    }

    @SerializedName("data")
    @Expose
    private List<Maindata> banner1;




    @Override
    public String toString() {
        return "EmployeeModel{" +

                ", data=" + banner1 +

                '}';
    }
}
