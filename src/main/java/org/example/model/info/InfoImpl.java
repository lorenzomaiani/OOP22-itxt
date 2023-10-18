package org.example.model.info;

import org.example.model.filemodel.FileModel;
import org.example.model.setting.Setting;

/**
 * Implementation of Info interface.
 */
public final class InfoImpl implements Info {

    private FileModel fileModel;
    private Setting setting;
    //private TextTrasformation trasformation.

    @Override
    public FileModel getFileModel() {
        return fileModel;
    }

    @Override
    public void setFileModel(final FileModel fileModel) {
        this.fileModel = fileModel;
    }

    @Override
    public Setting getSetting() {
        return setting;
    }

    @Override
    public void setSetting(final Setting setting) {
        this.setting = setting;
    }

}
