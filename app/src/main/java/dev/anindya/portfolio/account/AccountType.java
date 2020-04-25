package dev.anindya.portfolio.account;

import androidx.annotation.NonNull;

public enum AccountType {
    NONE(""),

    GITHUB("https://api.github.com/");

    private final String mBaseUrl;

    AccountType(@NonNull final String baseUrl) {
        mBaseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }
}
