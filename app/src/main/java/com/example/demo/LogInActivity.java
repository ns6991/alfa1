package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LogInActivity extends AppCompatActivity {
    Intent si;
    ArrayList<String> memList = new ArrayList<String>();
    ArrayList<Members> memInfoList = new ArrayList<Members>();
    EditText  password, phone;
    boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        phone = (EditText) findViewById(R.id.phoneET2);
        password = (EditText) findViewById(R.id.passwordET2);
    }

    private boolean isExist(String password, String phone){

        FBref.refMembers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {
                String str1,str2;

                memList.clear();
                memInfoList.clear();
                for(DataSnapshot data : dS.getChildren()) {
                    Members memTmp = data.getValue(Members.class);
                    if(memTmp.getPassword().equals(password) && memTmp.getPhone().equals(phone)) flag = true;

                }
            }
            public void onCancelled(DatabaseError databaseError) { }

        });
        return flag;
    }

    public void moveToSI(View view) {
        si =new Intent(this,SignInActivity.class);
        startActivity(si);
    }

    public void enterLI(View view) {
        if(isExist( password.getText().toString(), phone.getText().toString())){
            Toast.makeText(this, "login!", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "user doesn't exist", Toast.LENGTH_SHORT).show();

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