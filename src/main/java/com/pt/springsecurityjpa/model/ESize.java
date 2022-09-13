package com.pt.springsecurityjpa.model;

public enum ESize {

    XS("XS"),
    S("S"),
    M("M"),
    L("L"),
    XL("XL"),
    XXL("XXL"),
    _1XL("1XL"),
    _2XL("2XL"),
    _3XL("3XL");


    private final String displayValue;

    private ESize(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }


}
