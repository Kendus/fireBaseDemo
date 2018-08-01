package com.example.kendus.firebasedemo3;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends Activity {

    private DatabaseReference dB;
    private FirebaseUser user;
    private EditText nameEdit;
    private EditText ageEdit;
    private EditText genderEdit;
    private EditText passwordEdit;
    private EditText emailEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dB = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        nameEdit = findViewById(R.id.nameBox);
        ageEdit = findViewById(R.id.ageBox);
        genderEdit = findViewById(R.id.genderBox);
        passwordEdit = findViewById(R.id.passwordBox);
        emailEdit = findViewById(R.id.emailBox);
        //setContentView(R.layout.activity_sign_up);
    }

    public void signUp (View view){
        String name = nameEdit.getText().toString();
        String age = ageEdit.getText().toString();
        String gender = genderEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String email = emailEdit.getText().toString();


        newUser(age, name, gender);

    }
    public class User {
        String age;
        String username;
        String sex;
        int likes;


        public User (String age, String username, String sex){
            this.age = age;
            this.username = username;
            this.sex = sex;
            likes = 0;
        }
    }

    public void newUser(String age, String username, String sex)  {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        User newUser = new User(age, username, sex);
        dB.child("Users").child(uid).setValue(newUser);
    }
}
