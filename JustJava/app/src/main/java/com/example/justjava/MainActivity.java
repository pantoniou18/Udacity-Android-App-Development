package com.example.justjava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Submit order ro get coffee
     *
     */
    public void submitOrder(View view) {
        display();


        CheckBox whippedCreamCBox = (CheckBox) findViewById(R.id.whipped_cream_cbox);
        CheckBox chocolateCBox = (CheckBox) findViewById(R.id.chocolate_cbox);
        EditText nameEditText = (EditText)findViewById(R.id.name);


        String name = nameEditText.getText().toString();
        Boolean hasWhippedCream = whippedCreamCBox.isChecked();
        Boolean hasChocolate = chocolateCBox.isChecked();

        int price = calculatePrice(hasWhippedCream,hasChocolate);
        String orderSummary = getOrderSummary(name, price, hasWhippedCream, hasChocolate);
        sendOrderSummary(name,orderSummary);
    }

    /**
     * Increment quantity of the cups
     *
     */
    public void increment(View view) {
        if(quantity == 100){
            Toast.makeText(this, getString(R.string.max_quantity_err_msg),
                    Toast.LENGTH_SHORT).show();
        }else{
            quantity = quantity + 1;
            display();
        }
    }

    /**
     * Decrement quantity of the cups
     *
     */
    public void decrement(View view) {
        if(quantity == 1){
            Toast.makeText(this, R.string.min_quantity_err_msg,
                    Toast.LENGTH_SHORT).show();
        }else{
            quantity = quantity - 1;
            display();
        }

    }

    /**
     * Display quantity of the cups
     *
     */
    private void display() {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }

    /**
     * Calculates the price of the order.
     *
     * @param hasWhippedCream is whether or not we should include whipped cream topping in the price
     * @param hasChocolate    is whether or not we should include whipped cream topping in the price
     * @return total price
     */
    private int calculatePrice(Boolean hasWhippedCream, Boolean hasChocolate){
        int pricePerCup = 5;

        if(hasWhippedCream){
            pricePerCup += 1;
        }

        if(hasChocolate){
            pricePerCup += 2;
        }
        return (pricePerCup*quantity);
    }


    /**
     * Create summary of the order.
     *
     * @param name is the name of the person placing the order
     * @param price is the total price of the order
     * @param hasWhippedCream whether whipped cream topping is added
     * @param hasChocolate whether chocolate topping is added
     *
     * @return text summary
     */

    @SuppressLint("StringFormatMatches")
    private String getOrderSummary(String name, int price, Boolean hasWhippedCream, Boolean hasChocolate) {


        String orderSummaryText = String.format(getString(R.string.username)), username;
        orderSummaryText += "\n" + getString(R.string.order_summary_add_whipped_cream,hasWhippedCream);
        orderSummaryText += "\n" + getString(R.string.order_summary_add_chocolate,hasChocolate);
        orderSummaryText += "\n" + getString(R.string.order_summary_quantity,quantity);
        orderSummaryText += "\n" + getString(R.string.order_summary_price,NumberFormat.getCurrencyInstance().format(price));
        orderSummaryText += "\n" + getString(R.string.thank_you_msg);

        return orderSummaryText;
    }

    private void sendOrderSummary(String name, String orderSummary){
        String emailSubject = getString(R.string.order_summary_email_subject,name);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(emailIntent, getString(R.string.send_order_summary_msg)));
        }
    }
}