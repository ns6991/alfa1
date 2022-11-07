package com.example.demo;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class Notification extends AppCompatActivity {

    EditText notifET;

    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Calendar calendar;
    private MaterialTimePicker picker;
    boolean timePicked;
    Intent si;
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notifET = findViewById(R.id.notificationText);
        createNotificationChannel();
        timePicked = false;
    }

    public void notify(View view) {
        if (notifET.getText().toString().equals(""))
            Toast.makeText(this, "Write Something!!!", Toast.LENGTH_SHORT).show();
        else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(Notification.this, "Notification!");
            String notif = notifET.getText().toString();
            builder.setContentTitle("Message from android studio");
            builder.setAutoCancel(true);

            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Notification.this);
            managerCompat.notify(1, builder.build());
        }
    }

    public void pickTime(View view) {
        picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Alarm Time")
                .build();
        picker.show(getSupportFragmentManager(), "Notification!");

        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicked = true;
                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, picker.getHour());
                calendar.set(Calendar.MINUTE, picker.getMinute());
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
            }
        });

    }

    public void setAlarm(View view) {
        if (timePicked) {
            if (picker.getHour() > 12) {
                String st = "Scheduled Notification at: " + String.format("%02d", (picker.getHour() - 12)) + " : " + String.format("%02d", picker.getMinute()) + " PM";
                Toast.makeText(this, st, Toast.LENGTH_LONG).show();


            } else {
                String st = "Scheduled Notification at: " + picker.getHour() + " : " + picker.getMinute() + " AM";
                Toast.makeText(this, st, Toast.LENGTH_LONG).show();

            }
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            Toast.makeText(this, "Alarm set Successfully", Toast.LENGTH_SHORT).show();
            timePicked = false;
        } else {
            Toast.makeText(this, "Pick a time!!", Toast.LENGTH_SHORT).show();

        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Notification!", "my name", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home){
            si =new Intent(this,MainActivity.class);
            startActivity(si);
        }
        if (item.getItemId() == R.id.login){
            si =new Intent(this,LogInActivity.class);
            startActivity(si);
        }
        else if (item.getItemId() == R.id.signin){
            si = new Intent(this,SignInActivity.class);
            startActivity(si);
        }
        else if (item.getItemId() == R.id.gallery){
            si = new Intent(this,UploadImageGallery.class);
            startActivity(si);
        }
        if (item.getItemId() == R.id.image){
            si = new Intent(this,TakeApicture.class);
            startActivity(si);
        }
        if (item.getItemId() == R.id.chat){
            si = new Intent(this,Chat.class);
            startActivity(si);
        }
        if (item.getItemId() == R.id.notification){
            si = new Intent(this,Notification.class);
            startActivity(si);
        }
        if (item.getItemId() == R.id.googlepay){
            si = new Intent(this,GooglePAY.class);
            startActivity(si);
        }
        return true;
    }
}