package org.example.model.info;

import org.example.model.filemodel.FileModel;
import org.example.model.setting.Setting;

public class InfoImpl implements Info{

    private FileModel fileModel;
    private Setting setting;
    //private TextTrasformation trasformation.

    @Override
    public FileModel getFileModel() {
        return fileModel;
    }

    @Override
    public void setFileModel(FileModel fileModel) {
        this.fileModel = fileModel;
    }

    @Override
    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

}
