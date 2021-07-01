package com.diksha.employeedata;

import com.diksha.employeedata.ModelClass.EmployeeModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONHolder {

    @GET("getAllDetails")
    Call<EmployeeModel> getEmployeeModel();

}
