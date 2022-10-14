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
    public List<DirectoryConfig> queryChildDirectories(int parentNodeId) {
        List<DirectoryConfig> childDirectoryConfigs = mysqlConnector.getChildDirectoriesByParentId(parentNodeId);
        return childDirectoryConfigs;
    }

    @Override
    public int insertDirectoryInfo(DirectoryConfig dc) {
        int effectRow = mysqlConnector.insertDirectoryInfo(dc);
        return effectRow;
    }

    @Override
    public int queryNodeIdByParentIdAndCurPath(int parentId, String curPath) {
        int id = mysqlConnector.getDirectoryIdByParentIdAndPath(parentId, curPath);
        return id;
    }

    @Override
    public List<PartitionConfig> queryPartitions(int directory_id) {
        List<PartitionConfig> partitionFiles = mysqlConnector.getPartitionFiles(directory_id);
        return partitionFiles;
    }

    @Override
    public int removePartitionsByFileName(String fileName) {
        int effectRows = mysqlConnector.removePartitionByFileName(fileName);
        return  effectRows;
    }

    @Override
    public List<Integer> queryPartitionsLocations(String fileName) {
        List<Integer> partitionLocations = mysqlConnector.getPartitionLocations(fileName);
        return partitionLocations;
    }
}
