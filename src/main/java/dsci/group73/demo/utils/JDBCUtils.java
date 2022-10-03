package dsci.group73.demo.utils;

import dsci.group73.demo.entity.PartitionConfig;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class JDBCUtils {

    public static int uploadFileByPartitions(String filePath, String directory, int k){

        //id, fileName, directoryId, filePath, partitionName

        /***
         *  step1: update file_info
         *  step2: update table_info
         */

//        int directoryId = getDirectoryId(filePath);
//        if (directoryId == -1){
//            directoryId = createDirectory(filePath);
//        }
//        PartitionConfig partitionConfig = new PartitionConfig();
//        insertPartition(PartitionConfig);


        return 0;
    }

    public static Map<String, String[]> parseSql(String sql){
        Map<String, String[]> map = new HashMap<>();
        return map;
    }

    public String[] executeSqlInfo(Map<String,  String[]> map){
        //price : sum

        String temp = "price 100";

        String[] ret = new String[1];
        ret[0] = temp;
        return  ret;
    }


}
