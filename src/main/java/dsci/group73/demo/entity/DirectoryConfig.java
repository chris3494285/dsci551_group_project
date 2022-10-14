package dsci.group73.demo.entity;

public class DirectoryConfig {
    private int id;
    private String curPath;
    private int parentNodeId;

    public DirectoryConfig() {
    }

    public DirectoryConfig(int id, String curPath, int parentNodeId) {
        this.id = id;
        this.curPath = curPath;
        this.parentNodeId = parentNodeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurPath() {
        return curPath;
    }

    public void setCurPath(String curPath) {
        this.curPath = curPath;
    }

    public int getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(int parentNodeId) {
        this.parentNodeId = parentNodeId;
    }
}
