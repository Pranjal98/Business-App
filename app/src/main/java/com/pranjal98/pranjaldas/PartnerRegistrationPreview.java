package com.pranjal98.pranjaldas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PartnerRegistrationPreview extends AppCompatActivity {

    LinearLayout card, submitted;
    CheckBox checkBox;
    ImageView dp;
    TextView yourId, name, address, pin, mobile, email, vehicleType, drivingLicence, VehicleNumber;

    public void itemClicked(View v) {

        checkBox = (CheckBox)v;
    }

    public void submit(View view) {

        if(!checkBox.isChecked()){

            Toast.makeText(this, "Please Tick the Checkbox first", Toast.LENGTH_SHORT).show();
        }
        else {
            card.setVisibility(View.VISIBLE);
            submitted.setAlpha(0.1f);        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_registration_preview);

        card = findViewById(R.id.card);
        submitted = findViewById(R.id.sub);

        yourId = findViewById(R.id.yourId);
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        pin = findViewById(R.id.pinCode);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);

        vehicleType = findViewById(R.id.vehicleType);
        drivingLicence = findViewById(R.id.drivingLicence);
        VehicleNumber = findViewById(R.id.vahicleNumber);

        dp = findViewById(R.id.profilePic);

        String image_path= getIntent().getStringExtra("Vehicle Image");
        Uri fileUri = Uri.parse(image_path);
        dp.setImageURI(fileUri);

        String add = getIntent().getStringExtra("Address") + ", " + getIntent().getStringExtra("Pin Code");
        String[] array = add.split(" ");

        String temp = "";
        String sentence = "";

        for (String word : array) {

            if ((temp.length() + word.length()) < 20) {  // create a temp variable and check if length with new word exceeds textview width.

                temp += " "+word;

            } else {
                sentence += temp+"\n"; // add new line character
                temp = word;
            }

        }

        name.setText(getIntent().getStringExtra("Name"));
        address.setText(sentence + temp);
        mobile.setText(getIntent().getStringExtra("Mobile"));
        email.setText(getIntent().getStringExtra("Email"));
        yourId.setText(getIntent().getStringExtra("Aadhar Id"));
        vehicleType.setText(getIntent().getStringExtra("Vehicle Type"));
        drivingLicence.setText(getIntent().getStringExtra("Driving Licence"));
        VehicleNumber.setText(getIntent().getStringExtra("Vehicle Number"));
    }
}
