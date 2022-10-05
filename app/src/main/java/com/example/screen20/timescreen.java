package com.example.screen20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class timescreen extends AppCompatActivity {
    private UserDao mUserDao;

    TextView text;

    TextView buttonEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timescreen);

/*
        UserDatabase db = Room.databaseBuilder(this, UserDatabase.class, "User_db")  //final
                .allowMainThreadQueries()          // main thread에서 db에 입출력 가능하게 함
                .fallbackToDestructiveMigration()  //스키마(Database) 버전 변경 가능
                .build();
        mUserDao = db.userDao();
*/

        //데이터 삽입
/*        User user = new User();
        user.setMonday_start(540);
        user.setFriday_start(540);
        user.setWednesday_start();*/

        text = (TextView) findViewById(R.id.monday0);

        buttonEvent = (TextView) findViewById(R.id.monday0);

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(timescreen.this)
                        .setTitle("알람 저장")
                        .setMessage("1교시 (9:00)가 첫 수업이 맞습니까?")
                        .setIcon(android.R.drawable.ic_menu_save)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "저장을 완료 했습니다.", Toast.LENGTH_SHORT).show();
                                buttonEvent.setBackgroundColor(Color.LTGRAY); // 예를 누르면 색을 바꿔라

/*                                User user = new User();
                                user.setId(1);
                                user.setMonday_start(300);
                                mUserDao.setUpdateUser(user);*/

                                /*//데이터 보기
                                List<User> userList = mUserDao.getUserAll();
                                for (int i = 0; i < userList.size(); i++) {
                                    Log.d("Test", userList.get(i).getMonday_start() + "\n");
                                }*/
                        }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "취소하였습니다.",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }
}