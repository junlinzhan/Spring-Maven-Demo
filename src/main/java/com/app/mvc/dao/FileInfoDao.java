package com.app.mvc.dao;

import com.app.mvc.common.DatabaseRepository;
import com.app.mvc.domain.FileInfo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by jimin on 15/11/29.
 */
@DatabaseRepository
public interface FileInfoDao {

    FileInfo findByMD5(@Param("md5") String md5);

    FileInfo findById(@Param("id") int id);

    void insert(FileInfo fileInfo);
}
