package com.workshop.store.TextDecorator;

import java.util.ArrayList;
import java.util.List;

public class TextDecorator {
    private final List<String> texts = new ArrayList<>();

    public TextDecorator(String text) {
        this.texts.add(text);
    }

    public TextDecorator(String[] texts) {
        for (int i = 0; i < texts.length; i++) {
            this.texts.add(texts[i]);
        }
    }

    public List<String> getTexts() {
        return List.copyOf(texts);
    }

    public String getConcatenatedText(char delimiter) {
        return String.join(String.valueOf(delimiter), texts);
    }

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

    public TextDecorator partition(char partitionBy, int maxPartitionLength) {
        if (maxPartitionLength < 1) {
            return null;
        }

        List<String> newTexts = new ArrayList<>();
        List<String> parts = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (String str : this.texts) {
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                if (c == partitionBy || i % maxPartitionLength - 1 == 0) {
                    parts.add(current.toString());
                    current.setLength(0);
                }

                current.append(c);
            }

            newTexts.add(current.toString());
        }

        return new TextDecorator(newTexts.toArray(new String[0]));
    }

    public String decorate(char y, int length) {
        return decorate(y, length, true, '\n');
    }

    public String decorate(char y, int length, boolean centerText) {
        return decorate(y, length, centerText, '\n');
    }

    public String decorate(char y, int length, boolean centerText, char separator) {
        if (length < 1) {
            return "";
        }

        String decoratorLine = TextDecorator.multiplyChar(y, length);
        List<String> paddedTexts = new ArrayList<>();

        for (String str : this.texts) {
            if (str.length() < length) {
                paddedTexts.add(TextDecorator.centerText(str, length));
            }
        }

        String text = "\n" + String.join(String.valueOf(separator), paddedTexts) + "\n";

        return decoratorLine + text + decoratorLine;
    }
}
