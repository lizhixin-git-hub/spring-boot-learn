package com.lzx.file.dao;

import com.lzx.file.model.File;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FileDao {
    /**
     * 通过主键获取一行数据
     * @return 一行数据
     */
    File getById(Long id);

    /**
     * 插入一行数据
     * @param file 文件信息
     * @return 受影响行数
     */
    int save(File file);

    /**
     * 更新一行数据
     * @param file 文件信息
     * @return 受影响行数
     */
    int update(File file);

    /**
     * 删除一行数据
     * @param id 文件信息id
     * @return 受影响行数
     */
    int deleteById(Long id);

    /**
     * 根据一个或多个属性获取File
     * @param file 文件信息
     * @return 文件信息
     */
    File getByFile(File file);

}
