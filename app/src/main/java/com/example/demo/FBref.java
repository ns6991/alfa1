package com.example.demo;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FBref {
    public static FirebaseDatabase FBDB = FirebaseDatabase.getInstance("https://demoalfafb-default-rtdb.europe-west1.firebasedatabase.app/");
    public static DatabaseReference refMembers=FBDB.getReference("Members");

}
