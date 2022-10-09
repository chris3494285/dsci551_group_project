package dsci.group73.demo.service.impl;

import dsci.group73.demo.dao.MysqlMapper;
import dsci.group73.demo.entity.DirectoryConfig;
import dsci.group73.demo.entity.PartitionConfig;
import dsci.group73.demo.service.MySqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MySqlServiceImpl implements MySqlService {

    @Autowired
    MysqlMapper mysqlConnector;


    @Override
    public DirectoryConfig getDirectoryConfigByCurPath(String curPath,String parentPath) {
        DirectoryConfig directoryConfigByCurPath = mysqlConnector.getDirectoryConfigByCurPath(curPath, parentPath);
        return directoryConfigByCurPath;
    }

    @Override
    public int insertDirectoryConfig(DirectoryConfig directoryConfig) {
        int count = mysqlConnector.insertDirectory(directoryConfig);
        return count;
    }
}
