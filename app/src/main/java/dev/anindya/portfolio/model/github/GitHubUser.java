package dev.anindya.portfolio.model.github;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class GitHubUser implements Serializable {

    @SerializedName("avatar_url")
    @Expose
    String avatarUrl;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("login")
    @Expose
    String login;
    @SerializedName("company")
    @Expose
    String company;
    @SerializedName("email")
    @Expose
    String email;
    @SerializedName("blog")
    @Expose
    String blog;
    @SerializedName("public_repos")
    @Expose
    String publicRepos;
    @SerializedName("public_gists")
    @Expose
    String publicGists;
    @SerializedName("followers")
    @Expose
    String followers;
    @SerializedName("following")
    @Expose
    String following;
}
