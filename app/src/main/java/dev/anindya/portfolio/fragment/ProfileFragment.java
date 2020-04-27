package dev.anindya.portfolio.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.microsoft.device.dualscreen.layout.ScreenHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.anindya.portfolio.R;
import dev.anindya.portfolio.account.AccountType;
import dev.anindya.portfolio.model.github.GitHubUser;
import dev.anindya.portfolio.viewmodel.ProfileViewModel;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private static final String TAG = ProfileFragment.class.getSimpleName();

    @BindView(R.id.github_profile_text)
    TextView mGitHubProfileText;

    @BindView(R.id.profile_picture)
    ImageView mProfilePicture;

    ProfileViewModel mProfileViewModel;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProfileFragment.
     */
    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        initViewModel();
        return view;
    }

    private void initViewModel() {
        mProfileViewModel = new ViewModelProvider(requireActivity(),
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()))
                .get(ProfileViewModel.class);

        mProfileViewModel.getUserLiveData().observe(this, gitHubUser -> {
            Log.i(TAG, "user information received");
            loadProfileImage(gitHubUser.getAvatarUrl());

            loadDetailsFragment(gitHubUser);
        });
    }

    private void loadDetailsFragment(@NonNull final GitHubUser gitHubUser) {
        if (getActivity() != null && ScreenHelper.isDualMode(getActivity())) {
            if (getFragmentManager() != null) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(
                                R.id.dual_screen_end_container_id,
                                DetailsFragment.newInstance(AccountType.GITHUB, gitHubUser),
                                null
                        )
                        .commit();
            }
        } else {
            startDetailsFragment(gitHubUser);
        }
    }

    private void startDetailsFragment(GitHubUser gitHubUser) {
        if (getFragmentManager() != null) {
            getFragmentManager().beginTransaction()
                    .replace(
                            R.id.single_screen_container_id,
                            DetailsFragment.newInstance(AccountType.GITHUB, gitHubUser),
                            null
                    ).addToBackStack(null)
                    .commit();
        }
    }

    private void loadProfileImage(@NonNull final String avatarUrl) {
        Glide.with(getContext())
                .load(avatarUrl)
                .placeholder(R.drawable.ic_account)
                .apply(RequestOptions.circleCropTransform())
                .into(mProfilePicture);
    }

    @OnClick(R.id.get_data_btn)
    public void onGetData() {
        final String profileId = mGitHubProfileText.getText().toString().trim().toLowerCase();
        Log.i(TAG, String.format("Get data for %s", profileId));
        mProfileViewModel.loadGitHubProfile(profileId);
    }
}
