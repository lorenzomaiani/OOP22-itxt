package org.example.model.filemodel;



/**
 * Model of a file that can be saved and opened.
 */
public interface FileModel {

    /**
     * set the path of the file.
     * @param path the string representing the path of the file
     */
    void setFilePath(String path);

    /**
     * set the name of the file.
     * @param name the string representing the name of the file
     */
    void setFileName(String name);

    /**
     * get the path of the file.
     * @return the path
     */
    String getFilePath();

    /**
     * get the name of the file.
     * @return the name
     */
    String getFileName();
}
