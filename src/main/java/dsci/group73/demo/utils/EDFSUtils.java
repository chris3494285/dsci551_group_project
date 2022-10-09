package dsci.group73.demo.utils;

import dsci.group73.demo.constant.Constants;
import dsci.group73.demo.constant.ErrorMsg;
import dsci.group73.demo.entity.DirectoryConfig;
import dsci.group73.demo.service.MySqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EDFSUtils {

    @Autowired
    static MySqlService mySqlService;

    public static String readPartition(String input) {

      return null;
    }

    public static String getPartitionLocation(String input) {
        return null;
    }

    public static String putFile(String input) {
        return null;
    }

    public static String removeFile(String input) {
        return null;
    }

    public static String catFile(String input) {
        return null;
    }

    public static String lsDirectory(String input) {
        String[] parts = input.split("/");
        String partentPath = "root";
        String curPath = null;
        if (parts.length > 1){
            partentPath = parts[parts.length-2];
            curPath = parts[parts.length-1];
        } else {
            curPath = parts[parts.length-1];
        }

        return null;
    }

    public static String makeDirectory(String input) {
        String[] parts = input.split("/");
        String partentPath = "root";

        for (int i = 0; i < parts.length; i ++){
            String curPath = parts[i];
            DirectoryConfig directoryConfigByCurPath = mySqlService.getDirectoryConfigByCurPath(curPath, partentPath);
            if (null == directoryConfigByCurPath){
                DirectoryConfig dc = new DirectoryConfig();
                dc.setParentPath(partentPath);
                dc.setCurPath(curPath);
                int effectRow = mySqlService.insertDirectoryConfig(dc);
                if (effectRow != 1){
                    return ErrorMsg.EDFS_DIRECTORY_INSERT_ERROR;
                }
            } else {
                partentPath = curPath;
            }
        }

        return Constants.EDFS_MADIR_SUCCESS;
    }
}
