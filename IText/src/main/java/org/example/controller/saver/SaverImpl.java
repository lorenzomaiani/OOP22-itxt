package org.example.controller.saver;

import org.example.model.info.Info;

/**
 * Implementation class of Saver interface.
 */
public class SaverImpl implements Saver {
    private Info info;

    /**
     * Constructor.
     * @param info the info
     */
    public SaverImpl(final Info info) {
        this.info = info;
    }

    @Override
    public void saveSettingOnClose() {

    }

    @Override
    public void saveFileInfo() {

    }
}
