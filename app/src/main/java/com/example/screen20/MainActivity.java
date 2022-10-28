package com.example.screen20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private final int PERMISSION_REQUEST_RESULT = 100;

    private Button timescreen_move;
    private Button routescreen_move;
    Button thread_start, thread_stop;
    Thread thread;
    boolean isTread = false;

    TextView tv_location;
    Button bt_my_location;

    //new
    private static final int JOB_KEY = 101;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //new
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // JobScheduler 등록
        initJobScheduler();



        tv_location = (TextView)findViewById(R.id.tv_location);
        bt_my_location = (Button)findViewById(R.id.bt_my_location);
        bt_my_location.setOnClickListener(onClickListener);

        requestPermissionLocation();

        timescreen_move = findViewById(R.id.timescreen_move);
        timescreen_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, timescreen.class);
                startActivity(intent); // timescreen 이동
            }
        });

        routescreen_move = findViewById(R.id.routescreen_move);
        routescreen_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, routescreen.class);
                startActivity(intent); // routescreen 이동
            }
        });



        //스레드 시작
        thread_start = (Button)findViewById(R.id.thread_start);
        thread_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isTread = true;
                thread = new Thread() {
                    public void run() {
                        while(isTread) {
                            try {
                                sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            handler.sendEmptyMessage(0);
                        }
                    }
                };
                thread.start();
            }
        });

        //스레드 종료
        thread_stop = (Button)findViewById(R.id.thread_stop);
        thread_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isTread = false;

            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bt_my_location:
                    myLocationService();

                    break;
            }
        }
    };

    public void myLocationService(){
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,0,MainActivity.this);
            //GPS를 이용하여,6시간마다갱신함, 그러나 100m이상 위치이동 될 시 에만 자동으로 업데이트
        } catch (SecurityException e) {
            e.printStackTrace();
        }


        /*try {
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER); //NETWORK_PROVIDER 실내에서 이걸로 해라

            if (location != null) {
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd : HH-mm-ss");
                String getTime = simpleDateFormat.format(date);

                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                String msg = tv_location.getText().toString() + getTime + "\nlatitude:" + latitude + "\nlongitude:" + longitude + "\n---------------------\n";

                tv_location.setText(msg);
            }
        }catch (SecurityException e){
            e.printStackTrace();
        }*/
    }

    public boolean requestPermissionLocation() {
        int sdkVersion = Build.VERSION.SDK_INT;

        if (sdkVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERMISSION_REQUEST_RESULT);
                }
            }else {

            }
        }else{

        }

        return true;
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(getApplicationContext(), "위치기 업데이트 되었습니다.", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onLocationChanged(@NonNull Location location) {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd : HH-mm-ss");
        String getTime = simpleDateFormat.format(date);

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        String msg = tv_location.getText().toString() + getTime + "\nlatitude:" + latitude + "\nlongitude:" + longitude + "\n---------------------\n";

        tv_location.setText(msg);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }

    //new

    private void initJobScheduler() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ComponentName componentName = new ComponentName(this, JobService.class);
//            PersistableBundle bundle = new PersistableBundle();
//            bundle.putInt("number", 10);
            JobInfo.Builder builder = new JobInfo.Builder(JOB_KEY, componentName)
                    .setPersisted(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                // 버전마다 기간등록하는방법이 다르다해서 이렇게 작성했습니다.
                // 정확한건 더 찾아봐야 합니다.
                // 첫번째칸  간격 설정(최소시간 15분)
                // 두번째칸 이 작업에 대한 밀리초 플렉스. Flex는 기간의 최소 또는 5% 중 더 높은 값으로 고정됩니다
                builder.setPeriodic(15 * 60 * 1000, 20 * 60 * 1000);
            } else {
                builder.setPeriodic(15 * 60 * 1000);
            }
            JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            scheduler.schedule(builder.build());
        }
    }
}