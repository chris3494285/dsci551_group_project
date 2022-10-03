package dsci.group73.demo.entity;

import org.springframework.stereotype.Component;

@Component
public class PartitionConfig {
    private int id;
    private int directoryId;
    private String filePath;
    private String fileName;
    private String partitionName;

    public PartitionConfig() {
    }

    public PartitionConfig(int id, int directoryId, String filePath, String fileName, String partitionName) {
        this.id = id;
        this.directoryId = directoryId;
        this.filePath = filePath;
        this.fileName = fileName;
        this.partitionName = partitionName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(int directoryId) {
        this.directoryId = directoryId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPartitionName() {
        return partitionName;
    }

    public void setPartitionName(String partitionName) {
        this.partitionName = partitionName;
    }
}
