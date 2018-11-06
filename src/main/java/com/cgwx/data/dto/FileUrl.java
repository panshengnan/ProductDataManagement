package com.cgwx.data.dto;

public class FileUrl {

    private String fileName;
    private String fileUrl;
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileUrl(){
        return fileUrl;
    }

    public void setFileUrl(String fileUrl){
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();

    }

}
