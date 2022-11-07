package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Intent si;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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