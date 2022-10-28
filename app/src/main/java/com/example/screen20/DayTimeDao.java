package com.example.screen20;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DayTimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DayTime dayTime);

    @Delete
    void delete(DayTime dayTime);

    @Update
    void update(DayTime dayTime);

    @Query("SELECT * FROM DayTime")
    List<DayTime> getDayTimeAll();

    @Query("UPDATE DayTime SET time =:time WHERE day=:day")
    void updateday(int time, String day);

    @Query("DELETE FROM DayTime")
    void del();

    @Query("SELECT * FROM DayTime WHERE day=:day")
    List<DayTime> getDay(String day);


    //요일별 시간 업데이트
    @Query("UPDATE DayTime SET time = :time Where day = '월' ")
    void upmon(int time);

    @Query("UPDATE DayTime SET time = :time Where day = '화' ")
    void uptu(int time);

    @Query("UPDATE DayTime SET time = :time Where day = '수' ")
    void upwe(int time);

    @Query("UPDATE DayTime SET time = :time Where day = '목' ")
    void upth(int time);

    @Query("UPDATE DayTime SET time = :time Where day = '금' ")
    void upfr(int time);

    @Query("UPDATE DayTime SET time = :time Where day = '토' ")
    void upse(int time);


    //요일 받아오기
    @Query("SELECT day FROM DayTime WHERE day =:day")
    String getmoo(String day);

    //시간 받아오기
    @Query("SELECT time FROM DayTime WHERE day =:day")
    int gettime(String day);

    //전부 지우기기
   @Query("DELETE FROM DayTime")
    void clearall();
}