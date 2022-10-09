package dsci.group73.demo.service;

import dsci.group73.demo.entity.DirectoryConfig;
import dsci.group73.demo.entity.PartitionConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MySqlService {

    DirectoryConfig getDirectoryConfigByCurPath(String curPath, String parentPath);

    int insertDirectoryConfig(DirectoryConfig directoryConfig);
}
