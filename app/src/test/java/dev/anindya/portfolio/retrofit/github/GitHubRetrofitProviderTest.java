package dev.anindya.portfolio.retrofit.github;

import org.junit.Before;
import org.junit.Test;

import dev.anindya.portfolio.account.AccountType;
import okhttp3.HttpUrl;
import retrofit2.Retrofit;

import static org.junit.Assert.*;

public class GitHubRetrofitProviderTest {

    private GitHubRetrofitProvider mGitHubRetrofitProvider;

    @Before
    public void setUp() {
        mGitHubRetrofitProvider = new GitHubRetrofitProvider();
    }

    @Test
    public void getGitHubRetrofit() {
        Retrofit gitHubRetrofit = mGitHubRetrofitProvider.getGitHubRetrofit();
        assertEquals(HttpUrl.parse(AccountType.GITHUB.getBaseUrl()), gitHubRetrofit.baseUrl());
    }
}
