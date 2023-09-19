package org.example.controller.setting;

import org.example.model.setting.Setting;
import org.example.model.setting.SettingImpl;
import org.example.model.setting.Theme;

/**
 * Implementation of setting controller.
 */
public final class SettingControllerImpl implements SettingController {
    private final Setting setting = SettingImpl.getInstance();
    @Override
    public void setMainDirectoryInSetting(final String mainDirectoryPath) {
        setting.setMainDirectory(mainDirectoryPath);
    }

    @Override
    public void setFontInSetting(final String font) {
        setting.setMainFont(font);
    }

    @Override
    public void setAppTheme(final Theme theme) {
        setting.setAppTheme(theme);
    }
}
