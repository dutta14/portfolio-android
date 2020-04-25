package dev.anindya.portfolio.account;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTypeTest {

    private AccountType mAccountType;
    private static final String EXPECTED_GITHUB_API = "https://api.github.com/";

    @Before
    public void setUp() {
        mAccountType = AccountType.GITHUB;
    }

    @Test
    public void getBaseUrl() {
        assertEquals(EXPECTED_GITHUB_API, mAccountType.getBaseUrl());
    }
}