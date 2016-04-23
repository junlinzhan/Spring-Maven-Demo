package com.app.mvc.config;

import com.app.mvc.beans.PageQuery;
import com.app.mvc.common.DBRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jimin on 15/11/7.
 */
@DBRepository
public interface ConfigurationDao {

    Configuration findByK(@Param("k") String k);

    void updateByK(Configuration configuration);

    void insert(Configuration configuration);

    List<Configuration> getAll();

    List<Configuration> getByPage(PageQuery pageQuery);

    int count();
}
