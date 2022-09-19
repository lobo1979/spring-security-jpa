package com.pt.springsecurityjpa.model;

public enum ETitle {
    Mr("Mr."),
    Ms("Ms."),
    Mrs("Mrs."),
    Miss("Miss.");


    private final String displayValue;

    private ETitle(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }


}
