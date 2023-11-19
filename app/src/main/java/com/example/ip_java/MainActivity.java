package com.example.ip_java;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private Timer timer;
    private TextView textViewIpTime;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Executor executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

//        textViewIpTime = findViewById(R.id.text_view_ip_time);

//        fetchPublicIPAddress();
        startTimer();
    }
    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                fetchPublicIPAddress();
            }
        }, 0, 5000); // 每秒执行一次
    }
    private void fetchPublicIPAddress() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                final String publicIp = getPublicIPAddress();
                String public_time =getCurrentTime();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
//                        textViewIpTime.setText("Public IP: " + publicIp);
                        // 这里可以继续处理UI更新，例如显示时间
                        showNotification(publicIp+"_"+public_time);
                        Log.d("sss",publicIp);
                    }
                });
            }
        });
    }
    private String getPublicIPAddress() {
        try {
            URL url = new URL("https://api.ipify.org");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Unavailable";
        }
    }
    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

    private void showNotification(String ip) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel_id", "IP Address Channel", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setContentTitle("Current IP Address")
                .setContentText(ip)
                .setSmallIcon(android.R.drawable.ic_dialog_info);

        notificationManager.notify(1, builder.build());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
