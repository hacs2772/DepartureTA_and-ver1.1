package com.example.screen20; //데이터 액세스 오브젝트


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import kotlin.ParameterName;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setInsertUser(User2 user2);

    @Update
    void setUpdateUser(User2 user2);

    @Delete
    void setDeleteUser(User2 user2);

    @Query("Select * From User2 where monday_start")
    List<User2> getUserAll();

    @Query("Select * From User2 where monday_start")
    int getmonday();






}
