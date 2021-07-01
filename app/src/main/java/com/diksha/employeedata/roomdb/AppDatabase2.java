package com.diksha.employeedata.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.diksha.employeedata.ModelClass.RoomModel;


@Database(entities = {RoomModel.class}, version  = 1)
public abstract class AppDatabase2 extends RoomDatabase {

    public abstract RoomDao userDao();

    private static AppDatabase2 INSTANCE;

    public static AppDatabase2 getDbInstance(Context context) {

        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase2.class, "ROOM_DB")
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }
}
