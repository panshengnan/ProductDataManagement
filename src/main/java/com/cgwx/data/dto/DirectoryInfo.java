package com.cgwx.data.dto;

import java.util.List;

public class DirectoryInfo {

    private String singleTempId;

    private String directoryName;

    private List<String> fileListInDirectory;

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    public List<String> getFileListInDirectory() {
        return fileListInDirectory;
    }

    public void setFileListInDirectory(List<String> fileListInDirectory) {
        this.fileListInDirectory = fileListInDirectory;
    }

    public String getSingleTempId() {
        return singleTempId;
    }

    public void setSingleTempId(String singleTempId) {
        this.singleTempId = singleTempId;
    }
}
