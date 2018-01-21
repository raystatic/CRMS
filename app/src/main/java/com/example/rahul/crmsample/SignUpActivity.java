package com.example.rahul.crmsample;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import com.dd.CircularProgressButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    EditText editText_repass, editText_uname, editText_pass;
    //CircularProgressButton circularProgressButton;
    Button button;
    FirebaseAuth firebaseAuth;
    TextView textView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ActionBar actionBar=getSupportActionBar();

        actionBar.hide();

        firebaseAuth=FirebaseAuth.getInstance();

       // editText_org=(EditText)findViewById(R.id.et_org_signup);
        editText_uname=(EditText)findViewById(R.id.et_email_signup);
        editText_pass=(EditText)findViewById(R.id.et_pass_signup);
        editText_repass=(EditText)findViewById(R.id.et_repass_signup);
        //circularProgressButton=(CircularProgressButton)findViewById(R.id.btn_createaccount_signup)
        button=(Button)findViewById(R.id.btn_signup_signup);
        textView=(TextView)findViewById(R.id.tv_login_signup);

        textView.setText(Html.fromHtml("<u>Already a user? Login here</u>"));

        progressBar=(ProgressBar)findViewById(R.id.progressbar_signup);

        progressBar.setVisibility(View.GONE);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (checkEmail(editText_uname.getText().toString()) && countText(editText_pass.getText().toString())>=8 && textmatched(editText_repass.getText().toString(),editText_pass.getText().toString()))
//                {
//
                   // signUp(editText_uname.getText().toString(),editText_pass.getText().toString());
//                }
//                else
//                {
//                    signupfailed();
//                }
                initialiseProgressLoader();


            }
        });


    }


    public void initialiseProgressLoader()
    {

        if (progressBar.getProgress()==-1)
        {
            progressBar.setVisibility(View.GONE);
        }

        else
        {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setIndeterminate(true);
            progressBar.setProgress(30);
        }

        //button.setVisibility(View.GONE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                signUp(editText_uname.getText().toString(),editText_pass.getText().toString());

            }
        },5000);
    }

    public void signupfailedcheck()
    {

        if(!checkEmail(editText_uname.getText().toString()))
        {
            signupdialog("Invalid Username!");
        }
        else if(countText(editText_pass.getText().toString())<8)
        {
            signupdialog("Password must be at least of 8 characters!");
        }
        else if(!textmatched(editText_repass.getText().toString(),editText_pass.getText().toString()))
        {
            signupdialog("The two given passwords do not match. Both of them must be same!");
        }
        else
        {
            signupdialog("Attempt to Sign Up has failed. Please fill the fields carefully as instructed");
        }
        progressBar.setProgress(-1);
    }

    public void signUp(String email, String password)
    {
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"creataUser:success",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),PostSignUpActivity.class));
        //                    circularProgressButton.setProgress(100);
                            //button1.setText("Sign Up Success!");
                           // button.setVisibility(View.VISIBLE);
                           // progressBar.setProgress(100);

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"creataUser:failure|"+task.getException(),Toast.LENGTH_SHORT).show();
          //                  circularProgressButton.setProgress(-1);
                           // button1.setText("Sign Up Failure!");
                            //progressBar.setProgress(-1);
                            //button.setVisibility(View.VISIBLE);
                            signupdialog("Cannot Sign In now. Please try again!");

                        }
                    }
                });
    }

    public boolean checkEmail(String email)
    {
        if (email.endsWith("@gmail.com") || email.endsWith("@yahoo.com") || email.endsWith("@rediffmail.com") || email.endsWith("@hotmail.com")|| email.endsWith("yahoo.in"))
        {
            return true;
        }
        else
        return false;
    }

    public int countText(String pass)
    {
        return pass.length();
    }

    public boolean textmatched(String text1, String text2)
    {
        if (text1.equals(text2))
        {
            return true;
        }
        else
            return false;
    }

    public void signupdialog(String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(SignUpActivity.this);
        builder.setTitle("Sign up failed :(")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        editText_uname.setText(" ");
                        editText_pass.setText(" ");
                        startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }



}
