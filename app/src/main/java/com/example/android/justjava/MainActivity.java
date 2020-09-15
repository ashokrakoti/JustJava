/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    // still need to handle for the negative values of the coffees
    int number_of_coffees = 1;
    int unit_price = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * this method incerements the number of order by one when we tap on the + button in the ui
     * @param view
     */
    public void incrementOrder(View view) {
        if(number_of_coffees == 100){
            //shows an error message toast
            Toast.makeText(this, "You can't order more than 100 coffees.",Toast.LENGTH_SHORT).show();
             return ;
        }
        number_of_coffees += 1;

        display(number_of_coffees);
    }

    public void decrementOrder(View view) {
        if(number_of_coffees == 1){
            //display a toast message of what's wrong
            Toast.makeText(this, "You have to order at least one coffee", Toast.LENGTH_SHORT).show();
         return;
        }
        number_of_coffees -= 1;
        display(number_of_coffees);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        boolean hasWhippedCream = getCheckedStateForWhippedCream(view);
        boolean hasChocolate = getCheckedStateForChocolate(view);
        String consumerName = getCustomerName(view);
        int price = calculatePrice(number_of_coffees, unit_price, hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(price, consumerName, hasWhippedCream, hasChocolate);
        // add the code to start the new activity here.
        //use an intent.
        Intent displayOrder = new Intent(this, DisplayOrderActivity.class);
        displayOrder.putExtra("orderSummary", priceMessage);
        int orderItem = 0;
        if(hasChocolate && hasWhippedCream){
            //for both toppings together
            orderItem = 1;
        }
        else if(!hasWhippedCream && hasChocolate){
            //for only whipped cream toppings
           orderItem = 2;
        }
        else if (!hasChocolate && hasWhippedCream ){
            //for only chocolate toppings
            orderItem = 3;
        }else {
            orderItem = 4;
        }

        displayOrder.putExtra("orderItem",orderItem);
        if(displayOrder.resolveActivity(getPackageManager()) != null);
        startActivity(displayOrder);
        //displayMessage(priceMessage);
    }

    public String createOrderSummary(int price,String name, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name : " + name +
                "\nAdd whipped cream? " + String.valueOf(addWhippedCream) +
                "\nAdd Chocolate? " + String.valueOf(addChocolate) +
                " \nQuantity : " + String.valueOf(number_of_coffees) +
                "\nTotal : $" + price +
                "\nThank You!";
        return priceMessage;
    }

    /**
     * this calculate the total price of the cups of ordered coffee
     * @param quantity no of coffee cups ordered
     * @param price_per_coffee price of single cup of coffee
     * @return total price of ordered coffee
     */
    private int calculatePrice(int quantity, int price_per_coffee, boolean addWhippedCream, boolean addChocolate) {
        if(addWhippedCream){
            price_per_coffee += 1;
        }
        if(addChocolate){
            price_per_coffee += 2;
        }
        int price = quantity * price_per_coffee ;
        return price;
    }

    /**
     * This method displays the given quantity value on the screen
     * used to display the coffee number.
     * @param number  number of coffee cups ordered
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * this gets the check box state for the whipped cream checked or not.
     * @param view
     * @return true or false.
     */
    private boolean getCheckedStateForWhippedCream(View view) {
        CheckBox box = findViewById(R.id.notify_me_checkbox);
        return box.isChecked();
    }

    /**
     * this gets the check box state for the chocolate checked or not.
     * @param view
     * @return
     */
    private boolean getCheckedStateForChocolate(View view){
        CheckBox box = findViewById(R.id.chocolate_checkbox);
        return box.isChecked();
    }

    private String getCustomerName(View view){
        EditText name = findViewById(R.id.name_text_view);
        String consumerName =  (String) name.getText().toString();
        return consumerName;
    }
    /**
     * this method displays a message on the screen finally on order summary section.
     * @param message the string contains the message to be given to the screen.
     */
   /* private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }*/

}
