package com.example.rahul.crmsample;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText editText_email, editText_pass;
    Button button;
    FirebaseAuth firebaseAuth;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        firebaseAuth=FirebaseAuth.getInstance();

        editText_email=(EditText)findViewById(R.id.et_email_login);
        editText_pass=(EditText)findViewById(R.id.et_pass_login);
        button=(Button)findViewById(R.id.btn_login_login);
        textView=(TextView)findViewById(R.id.tv_register_login);

        textView.setText(Html.fromHtml("<u>New? Register Here</u>"));

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(editText_email.getText().toString(),editText_pass.getText().toString());
            }
        });

    }

    public void login(final String email, String password)
    {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"Login : succes",Toast.LENGTH_SHORT).show();

                            Intent intent=new Intent(getApplicationContext(),PostSignUpActivity.class);
                            intent.putExtra("email",email);
                            startActivity(intent);
                        }
                        else {
                           // Toast.makeText(getApplicationContext(),"Login : failure"+task.getException(),Toast.LENGTH_SHORT).show();
                            loginfailpopUp();
                        }
                    }
                });
    }

    public void loginfailpopUp()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this)
                .setTitle("Login Failed :(")
                .setCancelable(false)
                .setMessage("Attempt to login has failed. Please enter correct username and password")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
