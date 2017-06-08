package com.example.android.justjava;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
//        displayMessage(priceMessage);
        Intent eMail = new Intent(Intent.ACTION_SENDTO);
        eMail.setData(Uri.parse("mailto:")); // only email apps should handle this
        String subject = getString(R.string.eMailSubject) + " " + name;
        eMail.putExtra(Intent.EXTRA_SUBJECT, subject);
        eMail.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (eMail.resolveActivity(getPackageManager()) != null) {
            startActivity(eMail);
        }
    }

    /**
     * This method use to display receipt
     *
     * @param price is price of 1 cup of coffee
     */

    public String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String name) {
        String priceMessage = getString(R.string.order_summary_name) + ": " + name;
        priceMessage += "\n" + getString(R.string.addWhipped) + " " + hasWhippedCream;
        priceMessage += "\n" + getString(R.string.addChocolate) + " " + hasChocolate;
        priceMessage += "\n" + getString(R.string.qwt) + ": " + quantity;
        priceMessage += "\n" + getString(R.string.receiptAmount) + price;
        priceMessage += "\n" + getString(R.string.thank);
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
//    private void displayMessage(String message) {
//        TextView OrderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        OrderSummaryTextView.setText(message);
//    }

    /**
     * incrementing
     * when plus button is clicked
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, getString(R.string.incrementLimit), Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, getString(R.string.decrementLimit), Toast.LENGTH_SHORT).show();
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