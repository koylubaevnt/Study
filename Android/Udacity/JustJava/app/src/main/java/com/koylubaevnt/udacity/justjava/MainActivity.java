package com.koylubaevnt.udacity.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        int numberOfCoffes = quantity;

        EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
        String name = nameEditText.getText().toString();

        CheckBox whispedCreamCheckBox = (CheckBox) findViewById(R.id.whisped_cream_checkbox);
        boolean hasWhispedCream = whispedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate  = chocolateCheckBox.isChecked();

        int price = calculatePrice(hasWhispedCream, hasChocolate);
        String message = createOrderSummary(price, name, hasWhispedCream, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
//        display(numberOfCoffes);
//        displayMessage(message);
    }

    public void increment(View view) {
        if(quantity == 100) {
            return;
        }
        quantity++;
        display(quantity);
    }

    public void decrement(View view) {
        if(quantity == 1) {
            return;
        }
        quantity--;
        display(quantity);
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;
        if(addChocolate) {
            basePrice = basePrice + 1;
        }
        if(addWhippedCream) {
            basePrice = basePrice + 1;
        }
        return basePrice * quantity;
    }

    private String createOrderSummary(int price, String name, boolean addWhippedCream, boolean addChocolate) {
        return getString(R.string.order_summary_name, name) +
                "\n" + getString(R.string.order_summary_wipped_cream, addWhippedCream) +
                "\n" + getString(R.string.order_summary_wipped_cream, addChocolate) +
                "\n" + getString(R.string.quantity) + ": " + quantity +
                "\n" + getString(R.string.total) + " $" + price +
                "\n" + getString(R.string.thank_you);
    }
}
