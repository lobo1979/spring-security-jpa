package com.pt.springsecurityjpa.model;

public enum EColor {
    Red("Red"),
    Green("Green"),
    Purple("Purple"),
    Blue("Blue"),
    Black("Black"),
    Grey("Grey"),
    White("White");


    private final String displayValue;

    private EColor(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }


}
