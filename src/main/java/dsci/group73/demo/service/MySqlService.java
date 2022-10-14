package dsci.group73.demo.service;

import dsci.group73.demo.entity.DirectoryConfig;
import dsci.group73.demo.entity.PartitionConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MySqlService {

    List<DirectoryConfig> queryChildDirectories(int parentNodeId);

    int insertDirectoryInfo(DirectoryConfig dc);

    int queryNodeIdByParentIdAndCurPath(int parentId, String curPath);

    List<PartitionConfig> queryPartitions(int directory_id);

    int removePartitionsByFileName(String fileName);


    List<Integer> queryPartitionsLocations(String fileName);
}
