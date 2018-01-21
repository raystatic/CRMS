package com.example.rahul.crmsample;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    Button button_signUp, button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        firebaseAuth=FirebaseAuth.getInstance();

        button_signUp=(Button)findViewById(R.id.btn_signup_main);
        button_login=(Button)findViewById(R.id.btn_login_main);

        button_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

    }

    public void networkCheck()
    {
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if (networkInfo==null)
        {
            noInternetpopUp();
        }
    }

    public void noInternetpopUp()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this)
                .setTitle("Connection Error!")
                .setCancelable(false)
                .setMessage("Seems you are not connected to Internet. Please connect to Internet and try agin!")
                .setPositiveButton("Go to settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        networkCheck();

        FirebaseUser user=firebaseAuth.getCurrentUser();
        if (user!=null)
        {
            String email=user.getEmail();

            Intent intent=new Intent(getApplicationContext(),PostSignUpActivity.class);
            intent.putExtra("email",email);
            startActivity(intent);
        }

    }
}
