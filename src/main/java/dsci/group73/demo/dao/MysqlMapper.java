package dsci.group73.demo.dao;

import dsci.group73.demo.entity.DirectoryConfig;
import dsci.group73.demo.entity.PartitionConfig;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MysqlMapper {

    @Select("select * from file_info where directory_id = #{directoryId}")
    @Results({@Result(property = "id", column = "id"),@Result(property = "directoryId", column = "directory_id"),
            @Result(property = "filePath", column = "file_path"),@Result(property = "fileName", column = "file_name"),
            @Result(property = "partitionName", column = "partition_name")})
    List<PartitionConfig> getPartitionFiles(int directoryId);

    @Select("select distinct directory_id from file_info where file_name = #{fileName}")
    @Results({@Result(property = "id", column = "id"),@Result(property = "directoryId", column = "directory_id"),
            @Result(property = "filePath", column = "file_path"),@Result(property = "fileName", column = "file_name"),
            @Result(property = "partitionName", column = "partition_name")})
    List<Integer> getPartitionLocations(String fileName);


    @Select("select * from directory_info where parent_node_id = #{parentId}")
    @Results({@Result(property = "id", column = "id"),@Result(property = "curPath", column = "cur_path"),
            @Result(property = "parentNodeId", column = "parent_node_id")})
    List<DirectoryConfig> getChildDirectoriesByParentId(int parentId);

    @Insert("insert into directory_info (cur_path, parent_node_id) values (#{directoryConfig.curPath}, #{directoryConfig.parentNodeId})")
    int insertDirectoryInfo(DirectoryConfig directoryConfig);

    @Select("select id from directory_info where parent_node_id = #{parentId} and cur_path = curPath")
    int getDirectoryIdByParentIdAndPath(int parentId, String curPath);

    @Select("delete from file_info where file_path = #{fileName}")
    int removePartitionByFileName(String fileName);
}
