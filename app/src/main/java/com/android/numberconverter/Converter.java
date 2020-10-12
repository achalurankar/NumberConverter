package com.android.numberconverter;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Converter {
    public static final int OCTAL = 8;
    public static final int HEX = 16;
    public static final int BINARY = 2;

    private static HashMap<Character, Integer> codeMap = new HashMap<>();
    private static HashMap<Integer, Character> charMap = new HashMap<>();

    @NotNull
    public static String getDecimal(@NonNull String number, int base) {
        int exp = 0;
        long result = 0;

        for (int i = number.length() - 1; i >= 0; i--) {
            long coefficient;
            if (base == Converter.HEX)
                coefficient = Long.parseLong("" + codeMap.get(number.charAt(i)));
            else
                coefficient = Long.parseLong("" + number.charAt(i));
            result = result + coefficient * (pow(base, exp));
            exp++;
        }
        return String.valueOf(result);
    }

    @NotNull
    public static String convertDecimal(@NonNull String number, int base) {
        int n;
        n = Integer.parseInt(number);
        StringBuilder s = new StringBuilder();
        while (n / base != 0) {
            if (base == Converter.HEX)
                s.append(charMap.get(n % base));
            else
                s.append(n % base);
            n = n / base;
        }
        if (base == Converter.HEX)
            s.append(charMap.get(n % base));
        else
            s.append(n % base);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = s.toString().length() - 1; i >= 0; i--) {
            stringBuilder.append(s.toString().charAt(i));
        }
        return stringBuilder.toString();
    }

    public static void alphabetMapping() {
        int i;
        for (i = 0; i < 10; i++) {
            String s = "" + i;
            charMap.put(i, s.charAt(0));
        }
        for (char c = 'A'; c <= 'F'; c++) {
            charMap.put(i, c);
            i++;
        }
    }

    public static void codeMapping() {
        int i;
        for (i = 0; i < 10; i++) {
            String s = "" + i;
            codeMap.put(s.charAt(0), i);
        }
        for (char c = 'A'; c <= 'F'; c++) {
            codeMap.put(c, i);
            i++;
        }
    }

    public static long pow(int b, int e) {
        long r = 1;
        for (int i = 0; i < e; i++) {
            r = r * b;
        }
        return r;
    }
}
