package dsci.group73.demo.entity;

public class DirectoryConfig {
    private int id;
    private String curPath;
    private String parentPath;

    public DirectoryConfig() {
    }

    public DirectoryConfig(int id, String curPath, String parentPath) {
        this.id = id;
        this.curPath = curPath;
        this.parentPath = parentPath;
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

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }
}
