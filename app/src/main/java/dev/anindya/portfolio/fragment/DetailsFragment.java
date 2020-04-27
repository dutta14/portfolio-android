package dev.anindya.portfolio.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.anindya.portfolio.R;
import dev.anindya.portfolio.account.AccountType;
import dev.anindya.portfolio.model.github.GitHubUser;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {

    private static final String ACCOUNT_PROFILE = "account_profile";
    private static final String USER_KEY = "user_key";

    private String mAccountProfile;
    private GitHubUser mUser;

    public DetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param accountProfile The account whose profile we need to show.
     * @return A new instance of fragment DetailsFragment.
     */
    public static DetailsFragment newInstance(@NonNull final AccountType accountProfile,
                                              @NonNull final GitHubUser gitHubUser) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ACCOUNT_PROFILE, accountProfile.name());
        args.putSerializable(USER_KEY, gitHubUser);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAccountProfile = getArguments().getString(ACCOUNT_PROFILE);
            if (mAccountProfile.equals(AccountType.GITHUB.name())) {
                mUser = (GitHubUser) getArguments().getSerializable(USER_KEY);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);

        if (mUser != null) {
            name.setText(mUser.getName());
            handle.setText(mUser.getLogin());
            organization.setText(mUser.getCompany());
            email.setText(mUser.getEmail());
            website.setText(mUser.getBlog());
            repository_count.setText(mUser.getPublicRepos());
            gists_count.setText(mUser.getPublicGists());
            followers_count.setText(mUser.getFollowers());
            following_count.setText(mUser.getFollowing());
        }

        return view;
    }

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.handle)
    TextView handle;
    @BindView(R.id.organization)
    TextView organization;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.website)
    TextView website;
    @BindView(R.id.repository_count)
    TextView repository_count;
    @BindView(R.id.gists_count)
    TextView gists_count;
    @BindView(R.id.followers_count)
    TextView followers_count;
    @BindView(R.id.following_count)
    TextView following_count;
}
