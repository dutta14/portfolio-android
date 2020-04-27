package dev.anindya.portfolio.retrofit.github;

import dev.anindya.portfolio.account.AccountType;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubRetrofitProvider {

    public Retrofit getGitHubRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(AccountType.GITHUB.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
