package com.example.screen20; //유저 게터세터.java 고로 변경것은 없음, 새로운 디비데이터 추가시 처음에 해야할 부분

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Native;

@Entity
class User2 {
    @PrimaryKey(autoGenerate = true)
    private int id;                     //하나의 사용자에 대한 고유 ID값

    private int monday_start;
    private int tuesday_start;
    private int wednesday_start;
    private int thursday_start;
    private int friday_start;
    private int saturday_start;
    private double User2_lat;
    private double User2_long;

    //getter & setter = 가져오거나 or 세팅을 하기위한 준비 단계


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonday_start() {
        return monday_start;
    }

    public void setMonday_start(int monday_start) {
        this.monday_start = monday_start;
    }

    public int getTuesday_start() {
        return tuesday_start;
    }

    public void setTuesday_start(int tuesday_start) {
        this.tuesday_start = tuesday_start;
    }

    public int getWednesday_start() {
        return wednesday_start;
    }

    public void setWednesday_start(int wednesday_start) {
        this.wednesday_start = wednesday_start;
    }

    public int getThursday_start() {
        return thursday_start;
    }

    public void setThursday_start(int thursday_start) {
        this.thursday_start = thursday_start;
    }

    public int getFriday_start() {
        return friday_start;
    }

    public void setFriday_start(int friday_start) {
        this.friday_start = friday_start;
    }

    public int getSaturday_start() {
        return saturday_start;
    }

    public void setSaturday_start(int saturday_start) {
        this.saturday_start = saturday_start;
    }

    public double getUser2_lat() {
        return User2_lat;
    }

    public void setUser2_lat(double user2_lat) {
        User2_lat = user2_lat;
    }

    public double getUser2_long() {
        return User2_long;
    }

    public void setUser2_long(double user2_long) {
        User2_long = user2_long;
    }
}
