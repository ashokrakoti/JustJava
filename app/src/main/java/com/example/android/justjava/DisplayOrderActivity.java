package com.example.android.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_order);
        Intent displayOrder = getIntent();
        String orderSummary = displayOrder.getStringExtra("orderSummary");
        TextView textView = findViewById(R.id.order_display_view);

        ImageView coffee_image = findViewById(R.id.coffee_image_view);
        int orderItem = displayOrder.getIntExtra("orderItem",2);
        if(orderItem == 1){
            coffee_image.setImageResource(R.drawable.choclate_and_whipped_cream_coffee);
        }
        else if(orderItem == 2){
            coffee_image.setImageResource(R.drawable.coffee_whipped_cream);
        }
        else if(orderItem ==3){
            coffee_image.setImageResource(R.drawable.dark_chocolate_mocha_coffee);
        }
        else {
            coffee_image.setImageResource(R.drawable.cappuchino_image);
        }
        textView.setText(orderSummary);
    }

    public void sendOrder(View view) {
        TextView textView = findViewById(R.id.order_display_view);
        String orderSummary = (String)textView.getText();

        Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("plain/text");
        email.putExtra(Intent.EXTRA_EMAIL, new String[] { "ashokrakoti123@gmail.com" });
        email.putExtra(Intent.EXTRA_SUBJECT, "Order Summary");
        email.putExtra(Intent.EXTRA_TEXT, orderSummary);

        if(email.resolveActivity(getPackageManager())!= null) {
            startActivity(Intent.createChooser(email, "Send Email"));
        }
    }

}