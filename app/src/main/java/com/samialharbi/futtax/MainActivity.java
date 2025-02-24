package com.samialharbi.futtax;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText amountEditText1;
    private EditText amountEditText2;
    private TextView taxTextView;
    private TextView profitTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountEditText1 = findViewById(R.id.amountEditText1);
        amountEditText2 = findViewById(R.id.amountEditText2);
        taxTextView = findViewById(R.id.taxTextView);
        profitTextView = findViewById(R.id.profitTextView);

        amountEditText1.addTextChangedListener(textWatcher);
        amountEditText2.addTextChangedListener(textWatcher);
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            calculateTax();
        }
    };

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void calculateTax() {
        String amountString1 = amountEditText1.getText().toString();
        String amountString2 = amountEditText2.getText().toString();

        if (!amountString1.isEmpty() && !amountString2.isEmpty()) {
            double amount1 = Double.parseDouble(amountString1);
            double amount2 = Double.parseDouble(amountString2);

            double profit = amount2 - amount1;
            double tax = amount2 * 0.05; // 5% tax rate

            taxTextView.setText("Tax: " + String.format("%.0f", tax));
            taxTextView.setVisibility(View.VISIBLE);

            double netProfit = profit - tax;
            profitTextView.setText("Profit: " + String.format("%.0f", netProfit));
            profitTextView.setVisibility(View.VISIBLE);
        } else {
            taxTextView.setVisibility(View.GONE);
            profitTextView.setVisibility(View.GONE);
        }
    }
}