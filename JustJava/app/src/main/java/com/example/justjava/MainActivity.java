package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    TextView txtViewQuantity;
    TextView txtViewPrice;
    CheckBox chbxWhippedCream;
    CheckBox chbxChocolate;
    EditText editTextName;
    int quantity;
    double coffeePrice = 2.45;
    double whippedCreamPrice = 0.79;
    double chocolatePrice = 0.49;
    double total = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtViewQuantity = findViewById(R.id.txtView_quantity);
        txtViewPrice = findViewById(R.id.txtView_price);
        quantity = Integer.parseInt(txtViewQuantity.getText().toString());
        chbxWhippedCream = findViewById(R.id.chbx_whipped_cream);
        chbxChocolate = findViewById(R.id.chbx_chocolate);
        editTextName = findViewById(R.id.editTxtName);
    }

    public void submitOrder(View view) {
        calculatePrice();

    }

    private void calculatePrice() {
        total = quantity * coffeePrice;
        StringBuffer sb = new StringBuffer();
        sb.append(getString(R.string.order_summary, editTextName.getText()))
                .append("\nQuantity: ")
                .append(quantity);

        if (chbxWhippedCream.isChecked()) {
            total += whippedCreamPrice;
            sb.append("\nTopping : Whipped Cream");
        }
        if (chbxChocolate.isChecked()) {
            total += chocolatePrice;
            sb.append("\nTopping : Chocolate");
        }

        sb.append("\nTotal: " + NumberFormat.getCurrencyInstance().format(total))
                .append("\n" + getString(R.string.thank_you));

        // txtViewPrice.setText(sb);

        //Test Intent
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:nitish90choudhary@gmail.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava Order");
        intent.putExtra(Intent.EXTRA_TEXT, sb.toString());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void minusOne(View view) {
        if (quantity != 1)
            --quantity;
        txtViewQuantity.setText("" + quantity);
    }

    public void plusOne(View view) {
        ++quantity;
        txtViewQuantity.setText("" + quantity);
    }
}
