package com.android.numberconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText HexET;
    EditText DecimalET;
    EditText OctalET;
    EditText BinaryET;

    Boolean HexFocused = false;
    Boolean DecimalFocused = false;
    Boolean OctalFocused = false;
    Boolean BinaryFocused = false;
    Pattern HexPattern;
    Pattern DecimalPattern;
    Pattern OctalPattern;
    Pattern BinaryPattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HexET = findViewById(R.id.hex_et);
        DecimalET = findViewById(R.id.decimal_et);
        OctalET = findViewById(R.id.octal_et);
        BinaryET = findViewById(R.id.binary_et);

        HexPattern = Pattern.compile("[^0-9A-F]");
        DecimalPattern = Pattern.compile("[^0-9]");
        OctalPattern = Pattern.compile("[^0-7]");
        BinaryPattern = Pattern.compile("[^0-1]");

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
                    String hexNum = charSequence.toString().trim().toUpperCase();
                    Matcher matcher = HexPattern.matcher(hexNum);
                    if (hexNum.contains(" ") || matcher.find()) {
                        HexET.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.et_error_drawable));
                        return;
                    }
                    HexET.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.et_drawable));
                    DecimalET.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.et_drawable));
                    BinaryET.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.et_drawable));
                    OctalET.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.et_drawable));
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
                String Decimal = charSequence.toString().trim().toUpperCase();
                Matcher matcher = DecimalPattern.matcher(Decimal);
                if (Decimal.contains(" ") || matcher.find()) {
                    DecimalET.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.et_error_drawable));
                    return;
                }
                HexET.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.et_drawable));
                DecimalET.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.et_drawable));
                BinaryET.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.et_drawable));
                OctalET.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.et_drawable));
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
                String Octal = charSequence.toString().trim().toUpperCase();
                Matcher matcher = OctalPattern.matcher(Octal);
                if (Octal.contains(" ") || matcher.find()) {
                    OctalET.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.et_error_drawable));
                    return;
                }
                HexET.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.et_drawable));
                DecimalET.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.et_drawable));
                BinaryET.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.et_drawable));
                OctalET.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.et_drawable));
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
                String Binary = charSequence.toString().trim();
                Matcher matcher = OctalPattern.matcher(Binary);
                if (Binary.contains(" ") || matcher.find()) {
                    BinaryET.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.et_error_drawable));
                    return;
                }
                HexET.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.et_drawable));
                DecimalET.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.et_drawable));
                BinaryET.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.et_drawable));
                OctalET.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.et_drawable));
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