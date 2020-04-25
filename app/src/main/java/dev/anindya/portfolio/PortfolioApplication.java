package dev.anindya.portfolio;

import android.app.Application;

import com.microsoft.device.dualscreen.layout.SurfaceDuoScreenManager;

public class PortfolioApplication extends Application {
    private SurfaceDuoScreenManager surfaceDuoScreenManager;

    @Override
    public void onCreate() {
        super.onCreate();
        surfaceDuoScreenManager = SurfaceDuoScreenManager.init(this);
    }

    public SurfaceDuoScreenManager getSurfaceDuoScreenManager() {
        return surfaceDuoScreenManager;
    }
}
