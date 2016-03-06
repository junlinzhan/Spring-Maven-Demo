package com.app.mvc.dao;

import com.app.mvc.common.DatabaseRepository;
import com.app.mvc.domain.Admin;
import org.apache.ibatis.annotations.Param;

/**
 * Created by jimin on 15/11/2.
 */
@DatabaseRepository
public interface AdminDao {

    Admin findById(@Param("id") int id);

    Admin findByUsername(@Param("username") String username);
}
