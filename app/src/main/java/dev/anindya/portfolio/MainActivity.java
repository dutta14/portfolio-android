package dev.anindya.portfolio;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.microsoft.device.dualscreen.layout.ScreenModeListener;
import com.microsoft.device.dualscreen.layout.SurfaceDuoScreenManager;

import dev.anindya.portfolio.fragment.DetailsFragment;
import dev.anindya.portfolio.fragment.ProfileFragment;
import dev.anindya.portfolio.account.AccountType;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SurfaceDuoScreenManager surfaceDuoScreenManager =
                ((PortfolioApplication) getApplication()).getSurfaceDuoScreenManager();

        surfaceDuoScreenManager.addScreenModeListener(
                new ScreenModeListener() {
                    @Override
                    public void onSwitchToSingleScreenMode() {
                        getSupportFragmentManager().beginTransaction()
                                .replace(
                                        R.id.single_screen_container_id,
                                        ProfileFragment.newInstance(),
                                        null
                                )
                                .commit();
                    }

                    @Override
                    public void onSwitchToDualScreenMode() {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(
                                        R.id.dual_screen_start_container_id,
                                        ProfileFragment.newInstance(),
                                        null
                                )
                                .replace(
                                        R.id.dual_screen_end_container_id,
                                        DetailsFragment.newInstance(AccountType.NONE, null),
                                        null
                                ).commit();
                    }
                }
        );
    }
}
