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

public class SignInActivity extends AppCompatActivity {
    ArrayList<String> list_id = new ArrayList<>();
    EditText username, email, password, phone;
    Intent si;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        username = (EditText) findViewById(R.id.userNameET);
        email = (EditText) findViewById(R.id.emailET);
        password = (EditText) findViewById(R.id.passwordET);
        phone = (EditText) findViewById(R.id.phonenNumET);
    }

    public void enterSI(View view) {
        if(!isExist()){
            Members newMember = new Members(username.getText().toString(),email.getText().toString(),phone.getText().toString(),password.getText().toString());
            FBref.refMembers.child(phone.getText().toString()).setValue(newMember);

            Toast.makeText(this, "data saved", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "user already exist", Toast.LENGTH_SHORT).show();

    }





    private boolean isExist(){
        list_id.clear();
        FBref.refMembers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {
                for (DataSnapshot data : dS.getChildren()) {
                    list_id.add((String) data.getKey());
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
        return list_id.contains(phone.getText().toString());

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

    public void moveToLI(View view) {
        si =new Intent(this,LogInActivity.class);
        startActivity(si);
    }
}