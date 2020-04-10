package com.pranjal98.pranjaldas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class SellerRegistrationPreview extends AppCompatActivity {

    int chk= 0;
    LinearLayout card, submitted;
    CheckBox checkBox;
    ImageView shopImage, interiorShopImage;
    TextView yourId, name, address, pin, mobile, email, shopName, shopLocation, shopPin, shopContact, shopEmail, gstNo;

    public void itemClicked(View v) {

        chk = 1;
        checkBox = (CheckBox)v;
    }

    public void submit(View view) {

        if(chk == 1){

            if(checkBox.isChecked()){

                card.setVisibility(View.VISIBLE);
                submitted.setAlpha(0.1f);
            }
            else {
                Toast.makeText(this, "Please Tick the Checkbox first", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Please Tick the Checkbox first", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_registration_preview);

        card = findViewById(R.id.card);
        submitted = findViewById(R.id.sub);

        yourId = findViewById(R.id.yourId);
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        pin = findViewById(R.id.pinCode);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);

        shopName = findViewById(R.id.shopName);
        shopLocation = findViewById(R.id.chooseLocation);
        shopPin = findViewById(R.id.pinCode);
        shopContact = findViewById(R.id.contactNumber);
        shopEmail = findViewById(R.id.shopEmail);
        gstNo = findViewById(R.id.gstNumber);

        shopImage = findViewById(R.id.frontImage);
        interiorShopImage = findViewById(R.id.interiorShopImage);

        String image_path= getIntent().getStringExtra("imagePath");
        Uri fileUri = Uri.parse(image_path);
        shopImage.setImageURI(fileUri);

        String image_path_interior= getIntent().getStringExtra("interiorImagePath");
        Uri fileUriInterior = Uri.parse(image_path_interior);
        interiorShopImage.setImageURI(fileUriInterior);

        String add = getIntent().getStringExtra("Address") + ", " + getIntent().getStringExtra("Pin Code");
        String[] array = add.split(" ");

        String temp = "";
        String sentence = "";

        for (String word : array) {

            if ((temp.length() + word.length()) < 35) {  // create a temp variable and check if length with new word exceeds textview width.

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

        shopName.setText(getIntent().getStringExtra("Shop Name"));
        shopLocation.setText(getIntent().getStringExtra("Shop Location") + ", " + getIntent().getStringExtra("Shop Pin"));
        shopContact.setText(" " + getIntent().getStringExtra("Shop Contact"));
        shopEmail.setText(" " + getIntent().getStringExtra("Shop Email"));
        gstNo.setText(getIntent().getStringExtra("GST NO"));
    }
}
