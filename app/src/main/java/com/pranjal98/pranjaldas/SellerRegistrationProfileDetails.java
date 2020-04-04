package com.pranjal98.pranjaldas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SellerRegistrationProfileDetails extends AppCompatActivity {

    EditText yourId, name, address, pin, mobile, email, password, newPassword;

    public void next(View view) {

        if( name.getText().toString().trim().equals(""))
        {
            name.setError( "Your Name is required!" );
        }
        else if(yourId.getText().toString().trim().equals(""))
        {
            yourId.setError( "Your Aadhar Id is required!" );
        }
        else if(mobile.getText().toString().trim().equals(""))
        {
            mobile.setError( "Mobile Number is required!" );
        }
        else if(password.getText().toString().trim().equals(""))
        {
            password.setError( "Password is required!" );
        }
        else if(newPassword.getText().toString().trim().equals(""))
        {
            newPassword.setError("please re enter Password");
        }
        else if(!password.getText().toString().equals(newPassword.getText().toString())){

            Toast.makeText(this, "Sorry! Two Password didn't matched.", Toast.LENGTH_SHORT).show();
        }
        else {

            Intent intent = new Intent(getApplicationContext(), SellerRegistrationShopDetails.class);
            intent.putExtra("Aadhar Id", yourId.getText().toString());
            intent.putExtra("Name", name.getText().toString());
            intent.putExtra("Address", address.getText().toString());
            intent.putExtra("Pin Code", pin.getText().toString());
            intent.putExtra("Mobile", mobile.getText().toString());
            intent.putExtra("Email", email.getText().toString());
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_registration_profile_details);

        yourId = findViewById(R.id.yourId);
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        pin = findViewById(R.id.pinCode);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        newPassword = findViewById(R.id.newPassword);
    }
}
