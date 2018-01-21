package com.example.rahul.crmsample;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PostSignUpActivity extends AppCompatActivity {

   SectionPageAdapter sectionPageAdapter;
   ViewPager viewPager;
   RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_sign_up);

        relativeLayout=(RelativeLayout)findViewById(R.id.main_content);

        Intent intent=getIntent();
        String email=intent.getStringExtra("email");

        Snackbar.make(relativeLayout,"Logged In as "+email, BaseTransientBottomBar.LENGTH_LONG).show();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager=(ViewPager)findViewById(R.id.container);
        setUpViewPager(viewPager);

        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_post_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            confirmLogout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setUpViewPager(ViewPager viewPager)
    {
        sectionPageAdapter=new SectionPageAdapter(getSupportFragmentManager());
        sectionPageAdapter.addFragment(new Tab1_fragment(),"Read Records");
        sectionPageAdapter.addFragment(new Tab2_fragment(),"Add Records");
        viewPager.setAdapter(sectionPageAdapter);

    }

    public void confirmLogout()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(PostSignUpActivity.this);
        builder.setTitle("Welcome..")
                .setMessage("Are you sure want to Log out?")
                .setCancelable(true)
                .setPositiveButton("Log Out", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                        firebaseAuth.signOut();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }
}
