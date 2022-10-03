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

    @Insert("insert into directory_info (cur_path, parent_path) values (#{directoryConfig.curPath}, #{directoryConfig.parentPath})")
    int insertDirectory(@Param("directoryConfig") DirectoryConfig directoryConfig);

    @Select("select * from directory_info where id = #{id}")
    @Results({@Result(property = "id", column = "id"),
            @Result(property = "curPath", column = "cur_path"),
            @Result(property = "parentPath", column = "parent_path")})
    DirectoryConfig getDirectoryConfigById(int id);

    @Select("select * from directory_info where cur_path = #{curPath}")
    @Results({@Result(property = "id", column = "id"),
            @Result(property = "curPath", column = "cur_path"),
            @Result(property = "parentPath", column = "parent_path")})
    DirectoryConfig getDirectoryConfigByCurPath(String curPath);
}
