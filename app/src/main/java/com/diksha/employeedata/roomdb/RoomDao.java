package com.diksha.employeedata.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;

@Dao
public interface RoomDao {

    @Query("SELECT * FROM roommodel")
    List<com.diksha.employeedata.ModelClass.RoomModel> getAllUsers();

    @Insert
    void insertUser(com.diksha.employeedata.ModelClass.RoomModel... users);

    @Delete
    void delete(com.diksha.employeedata.ModelClass.RoomModel user);
}
