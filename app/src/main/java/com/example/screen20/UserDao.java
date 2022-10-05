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

    @Insert//(onConflict = OnConflictStrategy.REPLACE)
    void setInsertUser(User user);

    @Update
    void setUpdateUser(User user);

    @Delete
    void setDeleteUser(User user);

    @Query("Select * From User")
    List<User> getUserAll();

    @Query("Select * from User where id = 1")
    User getUser();




}
