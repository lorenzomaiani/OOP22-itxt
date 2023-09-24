package org.example.controller.saver;

import org.example.model.info.Info;

public class SaverImpl implements Saver{
    private Info info;

    public SaverImpl(Info info){
        this.info = info;
    }

    @Override
    public void saveSettingOnClose() {

    }

    @Override
    public void saveFileInfo() {

    }
}
