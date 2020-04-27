package dev.anindya.portfolio.retrofit.github;

import dev.anindya.portfolio.model.github.GitHubUser;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {

    @GET("users/{user}")
    Call<GitHubUser> getUser(@Path("user") String user);
}
