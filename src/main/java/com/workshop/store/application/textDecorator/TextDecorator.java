package com.workshop.store.application.textDecorator;

import java.util.ArrayList;
import java.util.List;

public class TextDecorator {
    public static String multiplyChar(char x, int multiply) {

        char[] array = new char[multiply];

        for (int i = 0; i < multiply; i++) {
            array[i] = x;
        }

        return new String(array);
    }

    public static String centerText(String text, int lineLength) {
        return TextDecorator.centerText(text, lineLength, ' ');
    }

    public static String centerText(String text, int lineLength, char padWith) {
        if (text.length() >= lineLength) {
            return text;
        }

        int padAmount = lineLength - text.length();

        int rest = padAmount % 2;
        int padLeft = Math.floorDiv(padAmount, 2) + rest;
        int padRight = Math.floorDiv(padAmount, 2);

        return TextDecorator.multiplyChar(padWith, padLeft)
                + text
                + TextDecorator.multiplyChar(padWith, padRight);
    }

    public static String[] partition(String[] strings, char partitionBy, int maxPartitionLength) {
        if (strings.length < 1 || maxPartitionLength < 1) {
            return null;
        }

        List<String> newTexts = new ArrayList<>();

        for (String str : strings) {
            String[] partitioned = TextDecorator.partition(str, partitionBy, maxPartitionLength);

            for (String partitionedString : partitioned) {
                newTexts.add(partitionedString);
            }
        }

        return newTexts.toArray(new String[0]);
    }

    public static String[] partition(String string, char partitionBy, int maxPartitionLength) {
        if (maxPartitionLength < 1) {
            return null;
        }

        List<String> parts = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);

            if (c == partitionBy) {
                parts.add(current.toString());
                current.setLength(0);
                continue;
            }

            if (current.length() == maxPartitionLength) {
                parts.add(current.toString());
                current.setLength(0);
            }

            current.append(c);
        }

        parts.add(current.toString());

        return parts.toArray(new String[0]);
    }

    public static String decorate(String text, char decorateWith, boolean multiline) {
        return decorate(text, decorateWith, text.length(), multiline);
    }

    public static String decorate(String text, char decorateWith, int length, boolean multiline) {
        if (length < 1) {
            return "";
        }

        if (multiline) {
            text = "\n" + text + "\n";
        }

        String decoratorLine = TextDecorator.multiplyChar(decorateWith, length);

        return decoratorLine + text + decoratorLine;
    }
}
