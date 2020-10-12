package com.android.numberconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText HexET;
    EditText DecimalET;
    EditText OctalET;
    EditText BinaryET;

    Boolean HexFocused = false;
    Boolean DecimalFocused = false;
    Boolean OctalFocused = false;
    Boolean BinaryFocused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HexET = findViewById(R.id.hex_et);
        DecimalET = findViewById(R.id.decimal_et);
        OctalET = findViewById(R.id.octal_et);
        BinaryET = findViewById(R.id.binary_et);

        HexET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                HexFocused = b;
            }
        });
        DecimalET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                DecimalFocused = b;
            }
        });
        OctalET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                OctalFocused = b;
            }
        });
        BinaryET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                BinaryFocused = b;
            }
        });

        //text watchers
        HexET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0 && HexFocused) {
                    String hexNum = charSequence.toString();
                    String Decimal = Converter.getDecimal(hexNum, Converter.HEX);
                    String Octal = Converter.convertDecimal(Decimal, Converter.OCTAL);
                    String Binary = Converter.convertDecimal(Decimal, Converter.BINARY);
                    DecimalET.setText(Decimal);
                    OctalET.setText(Octal);
                    BinaryET.setText(Binary);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        DecimalET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0)
                    return;
                if (!DecimalFocused)
                    return;
                String Decimal = charSequence.toString();
                String Hex = Converter.convertDecimal(Decimal, Converter.HEX);
                String Octal = Converter.convertDecimal(Decimal, Converter.OCTAL);
                String Binary = Converter.convertDecimal(Decimal, Converter.BINARY);
                HexET.setText(Hex);
                OctalET.setText(Octal);
                BinaryET.setText(Binary);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        OctalET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0)
                    return;
                if (!OctalFocused)
                    return;
                String Octal = charSequence.toString();
                String Decimal = Converter.getDecimal(Octal, Converter.OCTAL);
                String Hex = Converter.convertDecimal(Decimal, Converter.HEX);
                String Binary = Converter.convertDecimal(Decimal, Converter.BINARY);
                DecimalET.setText(Decimal);
                HexET.setText(Hex);
                BinaryET.setText(Binary);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        BinaryET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0)
                    return;
                if (!BinaryFocused)
                    return;
                String Binary = charSequence.toString();
                String Decimal = Converter.getDecimal(Binary, Converter.HEX);
                String Octal = Converter.convertDecimal(Decimal, Converter.OCTAL);
                String Hex = Converter.convertDecimal(Decimal, Converter.BINARY);
                DecimalET.setText(Decimal);
                OctalET.setText(Octal);
                HexET.setText(Hex);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Converter.codeMapping();
        Converter.alphabetMapping();
    }
}