package dev.anindya.portfolio.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dev.anindya.portfolio.model.github.GitHubUser;
import dev.anindya.portfolio.retrofit.github.GitHubRetrofitProvider;
import dev.anindya.portfolio.retrofit.github.GitHubService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileViewModel extends ViewModel {

    public static final String TAG = ProfileViewModel.class.getSimpleName();

    private final GitHubService mService;
    private final MutableLiveData<GitHubUser> mUserLiveData;

    public ProfileViewModel() {
        final Retrofit retrofit = new GitHubRetrofitProvider().getGitHubRetrofit();
        mService = retrofit.create(GitHubService.class);
        mUserLiveData = new MutableLiveData<>();
    }

    public void loadGitHubProfile(@NonNull final String userId) {
        final Call<GitHubUser> call = mService.getUser(userId);
        call.enqueue(new Callback<GitHubUser>() {
            @Override
            public void onResponse(@NonNull final Call<GitHubUser> call,
                                   @NonNull final Response<GitHubUser> response) {
                Log.i(TAG, "onResponse: success");
               final GitHubUser gitHubUser = response.body();
               mUserLiveData.postValue(gitHubUser);
            }

            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    public MutableLiveData<GitHubUser> getUserLiveData() {
        return mUserLiveData;
    }

}
