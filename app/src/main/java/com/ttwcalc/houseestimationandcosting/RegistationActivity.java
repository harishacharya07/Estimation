package com.ttwcalc.houseestimationandcosting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class RegistationActivity extends AppCompatActivity {
    private EditText userName, userPassword, userEmail, userAge;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    private ImageView userProfilePic;
    String email, name, age, password, userId;
    private static int PICK_IMAGE = 123;
    Uri imagePath;
    private FirebaseFirestore firebaseStorage;
    private StorageReference storageReference;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_registation);
        getSupportActionBar().hide();

        userName = (EditText) findViewById(R.id.etUserName);
        userPassword = (EditText) findViewById(R.id.etUserPassword);
        userEmail = (EditText) findViewById(R.id.etUserEmail);
        regButton = (Button) findViewById(R.id.btnRegister);
        userAge = (EditText) findViewById(R.id.etAge);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseFirestore.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            Intent i = new Intent(RegistationActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }


        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (userName.getText().toString().isEmpty() || userPassword.getText().toString().isEmpty() ||
                        userEmail.getText().toString().isEmpty() || userAge.getText().toString().isEmpty()) {
                    Toast.makeText(RegistationActivity.this, "Please fill all the deatails", Toast.LENGTH_SHORT).show();

                } else {

                    final String name = userName.getText().toString();
                    final String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();
                    final String user_organization = userAge.getText().toString();


                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                Toast.makeText(RegistationActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                                userId = firebaseAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = firebaseStorage.collection("users").document(userId);
                                Map<String, Object> user = new HashMap<>();
                                user.put("fname", name);
                                user.put("email", user_email);
                                user.put("orgnanization", user_organization);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        Toast.makeText(RegistationActivity.this, "User Profile", Toast.LENGTH_SHORT).show();

                                    }
                                });
                                startActivity(new Intent(RegistationActivity.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(RegistationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

    }
}