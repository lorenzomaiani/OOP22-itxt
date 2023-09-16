package org.example.model.filemodel;

import java.util.Date;
import java.util.Objects;

/**
 * FileModel implementation class.
 */
public class FileModelImpl implements FileModel {
    private String path;
    private String name;
    private Date creationDate;

    public FileModelImpl(final String path, final String name) {
        this.path = path;
        this.name = name;
        this.creationDate = new Date();
    }


    @Override
    public void setFilePath(final String path) {
        this.path = path;
    }

    @Override
    public void setFileName(final String name) {
        this.name = name;
    }

    @Override
    public String getFilePath() {
        return this.path;
    }

    @Override
    public String getFileName() {
        return this.name;
    }

    @Override
    public Date getFileCreationDate() {
        return this.creationDate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileModelImpl fileModel = (FileModelImpl) o;
        return path.equals(fileModel.path) && name.equals(fileModel.name) && creationDate.equals(fileModel.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, name, creationDate);
    }
}
