package com.pranjal98.pranjaldas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SellerRegistrationShopDetails extends AppCompatActivity {

    EditText shopName, shopPin, shopContact, shopEmail, gstNo;

    TextView restaurant, hotel, sweet, category;

    Button shopLocation;

    LocationManager locationManager;
    LocationListener locationListener;

    Uri img, interiorImg;

    String add = "";

    public void category(View view){

        restaurant.setVisibility(View.VISIBLE);
        hotel.setVisibility(View.VISIBLE);
        sweet.setVisibility(View.VISIBLE);

        restaurant.setEnabled(true);
        hotel.setEnabled(true);
        sweet.setEnabled(true);
    }
    public void restaurant(View view){

        category.setText("RESTAURANT");
        restaurant.setVisibility(View.INVISIBLE);
        hotel.setVisibility(View.INVISIBLE);
        sweet.setVisibility(View.INVISIBLE);

        restaurant.setEnabled(false);
        hotel.setEnabled(false);
        sweet.setEnabled(false);
    }
    public void hotel(View view){

        category.setText("HOTEL");
        restaurant.setVisibility(View.INVISIBLE);
        hotel.setVisibility(View.INVISIBLE);
        sweet.setVisibility(View.INVISIBLE);

        restaurant.setEnabled(false);
        hotel.setEnabled(false);
        sweet.setEnabled(false);
    }
    public void sweet(View view){

        category.setText("SWEET SHOP");
        restaurant.setVisibility(View.INVISIBLE);
        hotel.setVisibility(View.INVISIBLE);
        sweet.setVisibility(View.INVISIBLE);

        restaurant.setEnabled(false);
        hotel.setEnabled(false);
        sweet.setEnabled(false);
    }

    public void updateLocationInfo(Location location){

        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        try {

            String address = "Couldn't find address";

            List<Address> listAddresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            if(listAddresses!= null && listAddresses.size()> 0){

                address= "";

                if(listAddresses.get(0).getSubThoroughfare() != null){

                    address += listAddresses.get(0).getSubThoroughfare() + " ";
                }
                if(listAddresses.get(0).getThoroughfare() != null){

                    address += listAddresses.get(0).getThoroughfare() + ", ";
                }
                if(listAddresses.get(0).getLocality() != null){

                    address += listAddresses.get(0).getLocality();
                }
                if(listAddresses.get(0).getPostalCode() != null){

//                    address += listAddresses.get(0).getPostalCode();
                    shopPin.setText(listAddresses.get(0).getPostalCode());
                }

//                Toast.makeText(this, address, Toast.LENGTH_SHORT).show();
                add = address;
            }
            else {
//                Toast.makeText(this, "Address not comming", Toast.LENGTH_SHORT).show();
            }
        }
        catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void chooseLocation(View view){

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                updateLocationInfo(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 3);
        }

        else {

            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 10, 0, locationListener);

            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if(location!= null){

                updateLocationInfo(location);
            }
            else {

                Toast.makeText(this, "Sorry! Couldn't find address. Please check your GPS connection.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void shopInteriorImage(View view){

        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        else {

            intentFuncInteriorImage();
        }
    }

    public void shopImage(View view){

        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        else {

            intentFunc();
        }
    }

    public void intentFuncInteriorImage(){

        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, 2);
    }

    public void intentFunc(){

        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

                intentFunc();
            }

            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 0,locationListener);
            }

        }
    }
    public void submit(View view) {

        if( shopName.getText().toString().trim().equals(""))
        {
            shopName.setError( "Your Name is required!" );
        }
        else if(shopPin.getText().toString().trim().equals(""))
        {
            shopPin.setError( "Your Shop Pin Code Id is required!" );
        }
        else if(shopLocation.getText().toString().trim().equals(""))
        {
            shopLocation.setError( "Please choose your location!" );
        }
        else if(img == null){
            Toast.makeText(this, "Please choose your shop image", Toast.LENGTH_SHORT).show();
        }
        else if(interiorImg == null){
            Toast.makeText(this, "Please choose your shop interior image", Toast.LENGTH_SHORT).show();
        }
        else if(add.equals(null) || add.equals("")){

            Toast.makeText(this, "Please choose your Shop Address first!", Toast.LENGTH_SHORT).show();
        }
        else{

            Intent intent = new Intent(SellerRegistrationShopDetails.this, SellerRegistrationPreview.class);

            intent.putExtra("Aadhar Id", getIntent().getStringExtra("Aadhar Id"));
            intent.putExtra("Name", getIntent().getStringExtra("Name"));
            intent.putExtra("Address", getIntent().getStringExtra("Address"));
            intent.putExtra("Pin Code", getIntent().getStringExtra("Pin Code"));
            intent.putExtra("Mobile", getIntent().getStringExtra("Mobile"));
            intent.putExtra("Email", getIntent().getStringExtra("Email"));

            intent.putExtra("Shop Name", shopName.getText().toString());
            intent.putExtra("Shop Pin", shopPin.getText().toString());
            intent.putExtra("Shop Location", add);
            intent.putExtra("Shop Contact", shopContact.getText().toString());
            intent.putExtra("Shop Email", shopEmail.getText().toString());
            intent.putExtra("GST NO", gstNo.getText().toString());

            intent.putExtra("imagePath", img.toString());
            intent.putExtra("interiorImagePath", interiorImg.toString());

            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_registration_shop_details);

        shopName = findViewById(R.id.shopName);
        shopPin = findViewById(R.id.pinCode);
        shopLocation = findViewById(R.id.chooseLocation);
        shopContact = findViewById(R.id.contactNumber);
        shopEmail = findViewById(R.id.email);
        gstNo = findViewById(R.id.gstNumber);

        category = findViewById(R.id.category);
        restaurant = findViewById(R.id.restaurant);
        hotel = findViewById(R.id.hotel);
        sweet = findViewById(R.id.sweet);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null){

            Uri selectImg = data.getData();
            img = selectImg;
        }

        if(requestCode == 2 && resultCode == RESULT_OK && data != null){

            Uri selectImg = data.getData();
            interiorImg = selectImg;
        }
    }
}
