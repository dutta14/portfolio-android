package dev.anindya.portfolio.retrofit.github;

import dev.anindya.portfolio.account.AccountType;
import retrofit2.Retrofit;

public class GitHubRetrofitProvider {

    public Retrofit getGitHubRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(AccountType.GITHUB.getBaseUrl())
                .build();
    }
}
