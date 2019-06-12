package com.example.ahmet.swallow;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class KayitActivity extends AppCompatActivity {

    private EditText mEmailField;
    private EditText mNicknameField;
    private EditText mPasswordField;
    private EditText mRepasswordField;

    private Button mRegisterBtn;
    private Button mLoginBtn;

    private FirebaseAuth mauth;
    private Intent LoginIntent;
    private Intent RegisterIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        mRegisterBtn = (Button) findViewById(R.id.registerButonLogin);
        mLoginBtn = (Button) findViewById(R.id.loginButon);

        mEmailField = (EditText) findViewById(R.id.emailFieldLogin);
        mNicknameField = (EditText) findViewById(R.id.usernameFieldRegister);
        mPasswordField = (EditText) findViewById(R.id.passwordFieldLogin);
        mRepasswordField = (EditText) findViewById(R.id.repasswordFieldRegister);

        mauth = FirebaseAuth.getInstance(); // Firebase sınıfının referans olduğu nesneleri kullanmak için istek oluşturuyoruz

        LoginIntent = new Intent(this, LoginActivity.class);
        RegisterIntent = new Intent(this,KayitActivity.class);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(LoginIntent);
            }
        });

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmailField.getText().toString();
                String password = mPasswordField.getText().toString();
                String repassword = mRepasswordField.getText().toString();
                String userName = mEmailField.getText().toString();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || password.length()<6 ||
                        TextUtils.isEmpty(repassword) || TextUtils.isEmpty(userName))
                {
                    Toast.makeText(KayitActivity.this,"Alanlar bos gecilemez veya şifre kısa",Toast.LENGTH_LONG).show();
                }

                mauth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(KayitActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(!task.isSuccessful()){

                                    Toast.makeText(KayitActivity.this, "Yetkilendirme Hatası",
                                            Toast.LENGTH_LONG).show();
                                } else {

                                    String user_id = mauth.getCurrentUser().getUid();
                                    DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                                    DatabaseReference dm_db = FirebaseDatabase.getInstance().getReference().child("users");

                                    String userName = mNicknameField.getText().toString();
                                    String password = mPasswordField.getText().toString();
                                    String email = mEmailField.getText().toString();

                                    Map newUser = new HashMap();
                                    newUser.put("username",userName);
                                    newUser.put("password",password);
                                    newUser.put("email",email);

                                    current_user_db.setValue(newUser);

                                    dm_db.child(userName).child("password").setValue(password);

                                    startActivity(LoginIntent); //logine geçiş yapar
                                }
                            }
                        });
            }
        });
    }
}
