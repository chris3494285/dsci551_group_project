package dsci.group73.demo.controller;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import dsci.group73.demo.entity.DirectoryConfig;
import dsci.group73.demo.entity.PartitionConfig;
import dsci.group73.demo.service.MySqlService;
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

    @RequestMapping("/query/partitionFile")
    public @ResponseBody Object queryPartitionFiles(@RequestParam("input") String input){
        Map<String, Object> ret = new HashMap<>();
//        List<PartitionConfig> partitionsByParent = mySqlService.getPartitionsByParent(parentDir);
//        ret.put("partitionFileLists", partitionsByParent);

        int hasError = 0;
        if (!"".equals(input) && input.contains("mkdir")){
            String[] parts = input.split("/");
            String parentPath = "root";

            for (int i = 1; i < parts.length; i ++){
                DirectoryConfig temp = mySqlService.getDirectoryConfigByCurPath(parts[i].trim());
                if (temp == null){
                    DirectoryConfig dc  = new DirectoryConfig();
                    dc.setCurPath(parts[i].trim());
                    dc.setParentPath(parentPath);
                    int insertCount = mySqlService.insertDirectoryConfig(dc);
                    if (insertCount != 1){
                        hasError = 1;
                        break;
                    }
                }
                parentPath = parts[i].trim();
            }

            ret.put("status", "success");
        } else if (!"".equals(input) && input.contains("put")){
            String[] parts = input.split(" ");

        } else if (input.contains("readPartition")){
            try {
                FileReader fileReader = new FileReader("C:\\Users\\admin\\Documents\\USC Doc\\DSCI 551\\test.csv");
                CSVReader csvReader = new CSVReaderBuilder(fileReader)
                        .withSkipLines(1)
                        .build();
                List<String[]> allData = csvReader.readAll();
                ret.put("partitionContent", allData);
                ret.put("status", 1);

            }catch (Exception e){
                ret.put("errMsg", e.getMessage());
            }

        }
        return ret;
    }
}
