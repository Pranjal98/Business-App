package com.pranjal98.pranjaldas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignUp extends AppCompatActivity {

    public void partner(View view){

        Intent intent = new Intent(getApplicationContext(), PartnerRegistrationProfileDetails.class);
        startActivity(intent);
        finish();
    }

    public void seller(View view){

        Intent intent = new Intent(getApplicationContext(), SellerRegistrationProfileDetails.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
}
