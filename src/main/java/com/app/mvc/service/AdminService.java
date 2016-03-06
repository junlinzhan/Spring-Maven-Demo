package com.app.mvc.service;

import com.app.mvc.dao.AdminDao;
import com.app.mvc.domain.Admin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by jimin on 15/11/3.
 */
@Service
public class AdminService {

    @Resource
    private AdminDao adminDao;

    public Admin findByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        return adminDao.findByUsername(username);
    }

}
