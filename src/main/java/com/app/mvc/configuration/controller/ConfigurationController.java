package com.app.mvc.configuration.controller;

import com.app.mvc.acl.util.RequestHolder;
import com.app.mvc.beans.JsonData;
import com.app.mvc.beans.PageQuery;
import com.app.mvc.configuration.DatabaseConfig;
import com.app.mvc.configuration.domain.Configuration;
import com.app.mvc.configuration.service.ConfigurationService;
import com.app.mvc.configuration.vo.ConfigurationParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by jimin on 15/11/7.
 */
@Slf4j
@Controller
@RequestMapping("/config")
public class ConfigurationController {

    @Resource
    private ConfigurationService configurationService;

    @ResponseBody
    @RequestMapping("/reload.json")
    public JsonData reload() {
        log.info("reload config, username:{}", RequestHolder.getCurrentUser().getUsername());
        DatabaseConfig.loadAllConfig();
        return JsonData.success();
    }

    @ResponseBody
    @RequestMapping("/save.json")
    public JsonData save(ConfigurationParam param) {
        Configuration configuration = configurationService.saveOrUpdate(param);
        return JsonData.success(configuration);
    }

    @ResponseBody
    @RequestMapping("/page.json")
    public JsonData page(PageQuery page) {
        return JsonData.success(configurationService.getByPage(page));
    }
}
