package com.pranjal98.pranjaldas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class PartnerRegistrationVehicleDetails extends AppCompatActivity {

    EditText vehicleType, drivingLicence, VehicleNumber;
    Uri imgdl, imgVehicle;

    public void vehicle(View view){

        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        else {

            vh();
        }
    }

    public void drivingLicence(View view){

        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        else {

            dl();
        }
    }

    public void vh(){

        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, 2);
    }

    public void dl(){

        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

                dl();
            }
        }
    }

    public void submit(View view) {

        if( vehicleType.getText().toString().trim().equals(""))
        {
            vehicleType.setError( "Field is required!" );
        }
        else if(drivingLicence.getText().toString().trim().equals(""))
        {
            drivingLicence.setError( "Field is required!" );
        }
        else if(VehicleNumber.getText().toString().trim().equals(""))
        {
            VehicleNumber.setError( "Field is required!" );
        }
        else if(imgdl == null){
            Toast.makeText(this, "Driving Licence Image Required", Toast.LENGTH_SHORT).show();
        }
        else if(imgVehicle == null){
            Toast.makeText(this, "Vehicle Image Required", Toast.LENGTH_SHORT).show();
        }
        else {

            Intent intent = new Intent(getApplicationContext(), PartnerRegistrationPreview.class);

            intent.putExtra("Aadhar Id", getIntent().getStringExtra("Aadhar Id"));
            intent.putExtra("Name", getIntent().getStringExtra("Name"));
            intent.putExtra("Address", getIntent().getStringExtra("Address"));
            intent.putExtra("Pin Code", getIntent().getStringExtra("Pin Code"));
            intent.putExtra("Mobile", getIntent().getStringExtra("Mobile"));
            intent.putExtra("Email", getIntent().getStringExtra("Email"));

            intent.putExtra("Vehicle Type", vehicleType.getText().toString());
            intent.putExtra("Driving Licence", drivingLicence.getText().toString());
            intent.putExtra("Vehicle Number", VehicleNumber.getText().toString());

            intent.putExtra("DL Image", imgdl.toString());
            intent.putExtra("Vehicle Image", imgVehicle.toString());

            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_registration_vehicle_details);

        vehicleType = findViewById(R.id.vehicleType);
        drivingLicence = findViewById(R.id.drivingLicence);
        VehicleNumber = findViewById(R.id.vahicleNumber);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null){

            Uri selectImg = data.getData();
            imgdl = selectImg;
        }

        if(requestCode == 2 && resultCode == RESULT_OK && data != null){

            Uri selectImg = data.getData();
            imgVehicle = selectImg;
        }
    }
}
