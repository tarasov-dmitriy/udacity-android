package com.example.android.justjava;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.duration;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //learn name of the user
        EditText nameField = (EditText) findViewById(R.id.edit_message);
        String name = nameField.getText().toString();

        //check if whipped cream is needed
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream);
        boolean hasWhippedCream = whippedCream.isChecked();

        //check if chocolate is needed
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        boolean hasChocolate = chocolate.isChecked();
        int price = calculatePrice(hasWhippedCream, hasChocolate);

        //String priceMessage = "Total: $" + price;
        //priceMessage = priceMessage + "\nThank You!";
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, name);
        displayMessage(priceMessage);
    }

    /**
     * This method use to display receipt
     *
     * @param price is price of 1 cup of coffee
     */

    public String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String name) {
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream? " + hasWhippedCream;
        priceMessage += "\nAdd chocolate? " + hasChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }

    /**
     * Calculates the price of the order.
     *
     * @param hasChocolate - if yes + 2$
     * @param hasWhippedCream - if yes + 1$
     * @return price is the quantity * pricePerCup
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate ) {
        int basePrice = 5;
        if (hasWhippedCream) {
            basePrice += 1;
        }
        if (hasChocolate) {
            basePrice += 2;
        }
        return quantity * basePrice;
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView OrderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        OrderSummaryTextView.setText(message);
    }

    /**
     * incrementing
     * when plus button is clicked
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "Quantity can't be more than 100");
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * decrementing
     * when minus button is clicked
     */
    public void decrement(View view) {
        if (quantity == 1) {
            showToast("Quantity can't be less than 1");
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}