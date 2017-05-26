package com.telkom.weedu.data.api.model.edumail;

/**
 * Created by sidiqpermana on 4/30/17.
 */

public class AttachmentUploadStatus {
    private String name;
    private String status;
    private String filePath;

    public AttachmentUploadStatus(String filePath, String name, String status) {
        this.filePath = filePath;
        this.name = name;
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }
}
