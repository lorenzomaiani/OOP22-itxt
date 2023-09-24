package org.example.model.info;

import org.example.model.filemodel.FileModel;
import org.example.model.setting.Setting;

/**
 * Info class, used to load and storage info for the application.
 */
public interface Info {

    /**
     * Get the file model.
     *
     * @return the loaded file
     */
    public FileModel getFileModel();

    /**
     * Set a new File model, after opening a new one.
     *
     * @param fileModel the file
     */
    public void setFileModel(FileModel fileModel);

    /**
     * Get the setting.
     *
     * @return the setting
     */
    public Setting getSetting();

}