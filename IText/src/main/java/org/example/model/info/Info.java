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
    FileModel getFileModel();

    /**
     * Set a new File model, after opening a new one.
     *
     * @param fileModel the file
     */
   void setFileModel(FileModel fileModel);

    /**
     * Get the setting.
     *
     * @return the setting
     */
    Setting getSetting();

    /**
     * Set setting.
     * @param setting to set.
     */
    void setSetting(Setting setting);

}
