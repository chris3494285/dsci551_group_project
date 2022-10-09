package dsci.group73.demo.controller;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import dsci.group73.demo.constant.Constants;
import dsci.group73.demo.constant.ErrorMsg;
import dsci.group73.demo.entity.DirectoryConfig;
import dsci.group73.demo.entity.PartitionConfig;
import dsci.group73.demo.service.MySqlService;
import dsci.group73.demo.utils.EDFSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AppController {

    @Autowired
    MySqlService mySqlService;

    //    @RequestMapping("/query/partitionFile")
//    public @ResponseBody Object queryPartitionFiles(@RequestParam("input") String input){
//        Map<String, Object> ret = new HashMap<>();
////        List<PartitionConfig> partitionsByParent = mySqlService.getPartitionsByParent(parentDir);
////        ret.put("partitionFileLists", partitionsByParent);
//
//        int hasError = 0;
//        if (!"".equals(input) && input.contains("mkdir")){
//            String[] parts = input.split("/");
//            String parentPath = "root";
//
//            for (int i = 1; i < parts.length; i ++){
//                DirectoryConfig temp = mySqlService.getDirectoryConfigByCurPath(parts[i].trim());
//                if (temp == null){
//                    DirectoryConfig dc  = new DirectoryConfig();
//                    dc.setCurPath(parts[i].trim());
//                    dc.setParentPath(parentPath);
//                    int insertCount = mySqlService.insertDirectoryConfig(dc);
//                    if (insertCount != 1){
//                        hasError = 1;
//                        break;
//                    }
//                }
//                parentPath = parts[i].trim();
//            }
//
//            ret.put("status", "success");
//        } else if (!"".equals(input) && input.contains("put")){
//            String[] parts = input.split(" ");
//
//        } else if (input.contains("readPartition")){
//            try {
//                FileReader fileReader = new FileReader("C:\\Users\\admin\\Documents\\USC Doc\\DSCI 551\\test.csv");
//                CSVReader csvReader = new CSVReaderBuilder(fileReader)
//                        .withSkipLines(1)
//                        .build();
//                List<String[]> allData = csvReader.readAll();
//                ret.put("partitionContent", allData);
//                ret.put("status", 1);
//
//            }catch (Exception e){
//                ret.put("errMsg", e.getMessage());
//            }
//
//        }
//        return ret;
//    }
    @RequestMapping("/query/partitionFile")
    public @ResponseBody
    Object queryPartitionFiles(@RequestParam("input") String input) {
        Map<String, Object> ret = new HashMap<>();

        String commandCheck = checkCommand(input);

        if (ErrorMsg.COMMAND_CHECK_PASSED.equals(commandCheck)){
            String[] parts = input.split(" ");
            String command = parts[0];

            String output = null;
            switch (command){
                case Constants.EDFS_MKDIR:
                    output = EDFSUtils.makeDirectory(input);
                case  Constants.EDFS_LS:
                    output =EDFSUtils.lsDirectory(input);
                case  Constants.EDFS_CAT:
                    output =EDFSUtils.catFile(input);
                case  Constants.EDFS_RM:
                    output =EDFSUtils.removeFile(input);
                case  Constants.EDFS_PUT:
                    output =EDFSUtils.putFile(input);
                case  Constants.EDFS_GET_PARTITION_LOCATIONS:
                    output =EDFSUtils.getPartitionLocation(input);
                case  Constants.EDFS_READ_PARTITION:
                    output =EDFSUtils.readPartition(input);
            }

            ret.put("status", "fail");
            ret.put("ErrMsg", commandCheck);

        } else {
            ret.put("status", "fail");
            ret.put("ErrMsg", commandCheck);
        }

        return ret;
    }



    private String checkCommand(String input) {

        String[] parts = input.split(" ");

        if (parts.length < 2){
            return  ErrorMsg.COMMAND_PARAM_NUMBER_UNMATCH;
        } else {
            String[] possibleCommand = {Constants.EDFS_MKDIR,Constants.EDFS_LS, Constants.EDFS_CAT,
                    Constants.EDFS_RM, Constants.EDFS_PUT,Constants.EDFS_GET_PARTITION_LOCATIONS,Constants.EDFS_READ_PARTITION};
            String inputCommand = parts[0];

            for (String command :possibleCommand){
                if (command.equals(inputCommand)){
                    return ErrorMsg.COMMAND_CHECK_PASSED;
                }
            }

            return ErrorMsg.COMMAND_CANNOT_FOUND;
        }
    }
}
