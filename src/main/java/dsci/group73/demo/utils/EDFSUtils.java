package dsci.group73.demo.utils;

import dsci.group73.demo.constant.Constants;
import dsci.group73.demo.constant.ErrorMsg;
import dsci.group73.demo.entity.DirectoryConfig;
import dsci.group73.demo.entity.PartitionConfig;
import dsci.group73.demo.service.MySqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class EDFSUtils {

    @Autowired
    static MySqlService mySqlService;

    public static String readPartition(String input) {

        return null;
    }

    public static String getPartitionLocation(String input) {
        List<Integer> parentDirectoryIds = mySqlService.queryPartitionsLocations(input);
        if (null == parentDirectoryIds){
            return ErrorMsg.COMMAND_INPUT_PATH_CANNOT_FOUND;
        }

        String ret = "";
        for (int id : parentDirectoryIds){
            ret += id + " ";
        }

        return ret;
    }

    public static String putFile(String input) {
        return null;
    }

    public static String removeFile(String input) {
        int parentId = getDirectoryIdByPath(input.substring(0,input.lastIndexOf('/')));
        if (parentId == -1){
            return ErrorMsg.COMMAND_INPUT_PATH_CANNOT_FOUND;
        }
        String[] parts = input.split("/");
        int rows = mySqlService.removePartitionsByFileName(parts[parts.length - 1]);
        if (rows == 0){
            return ErrorMsg.COMMAND_REMOVE_TARGET_FILE_NOT_FOUND;
        }

        return Constants.EDFS_REMOVE_FILE_SUCCESS;
    }

    public static String catFile(String input) {

        int parentId = getDirectoryIdByPath(input.substring(0,input.lastIndexOf('/')));
        if (parentId == -1){
            return ErrorMsg.COMMAND_INPUT_PATH_CANNOT_FOUND;
        }

        return null;
    }

    public static String lsDirectory(String input) {

        int nodeId = getDirectoryIdByPath(input);
        if (nodeId == -1){
            return ErrorMsg.COMMAND_INPUT_PATH_CANNOT_FOUND;
        }

        String ret = "";
        List<DirectoryConfig> directoryConfigs = mySqlService.queryChildDirectories(nodeId);
        for (DirectoryConfig dc : directoryConfigs){
            ret += dc.getCurPath() + "\n";
        }

        List<PartitionConfig> partitionConfigs = mySqlService.queryPartitions(nodeId);
        for (PartitionConfig pc : partitionConfigs){
            ret += pc.getFileName();
        }

        return ret;
    }

    public static String makeDirectory(String input) {
        if ("/".equals(input) || getDirectoryIdByPath(input) != -1) {
            return ErrorMsg.COMMAND_PATH_ALREADY_EXISTED;
        }

        String[] parts = input.split("/");
        int parentId = Constants.EDFS_ROOT_ID;

        for (int i = 1; i < parts.length; i++) {
            List<DirectoryConfig> directoryConfigs = mySqlService.queryChildDirectories(parentId);
            int targetChildId = getTargetChildId(directoryConfigs, parts[i]);
            if (targetChildId == -1) {
                DirectoryConfig dc = new DirectoryConfig();
                dc.setCurPath(parts[i]);
                dc.setParentNodeId(parentId);
                int row = mySqlService.insertDirectoryInfo(dc);
                if (row != 1) {
                    return ErrorMsg.EDFS_DIRECTORY_INSERT_ERROR;
                }
                parentId = mySqlService.queryNodeIdByParentIdAndCurPath(parentId, parts[i]);
            } else {
                parentId = targetChildId;
            }

        }

        return Constants.EDFS_MADIR_SUCCESS;
    }

    private static int getTargetChildId(List<DirectoryConfig> directoryConfigs, String part) {
        if (null == directoryConfigs){
            return -1;
        }
        for (DirectoryConfig child : directoryConfigs) {
            if (child.getCurPath().equals(part)) {
                return child.getId();
            }
        }
        return -1;
    }



    public static int getDirectoryIdByPath(String path) {
        String[] parts = path.split("/");
        if (parts.length == 0) {
            return Constants.EDFS_ROOT_ID;
        } else {
            int parentRootId = Constants.EDFS_ROOT_ID;

            for (int i = 1; i < parts.length; i++) {
                List<DirectoryConfig> directoryConfigs = mySqlService.queryChildDirectories(parentRootId);
                if (i < parts.length - 1) {
                    for (DirectoryConfig dc : directoryConfigs) {
                        if (dc.getCurPath().equals(parts[i])) {
                            parentRootId = dc.getId();
                        }
                    }
                } else {
                    for (DirectoryConfig dc : directoryConfigs) {
                        if (dc.getCurPath().equals(parts[i])) {
                            return dc.getId();
                        }
                    }
                }

            }
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}
