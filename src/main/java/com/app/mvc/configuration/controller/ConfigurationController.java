package com.app.mvc.configuration.controller;

import com.app.mvc.acl.util.RequestHolder;
import com.app.mvc.beans.JsonData;
import com.app.mvc.beans.JsonMapper;
import com.app.mvc.beans.PageQuery;
import com.app.mvc.beans.PageResult;
import com.app.mvc.configuration.DatabaseConfig;
import com.app.mvc.configuration.service.ConfigurationService;
import com.app.mvc.configuration.vo.ConfigurationParam;
import com.app.mvc.configuration.domain.Configuration;
import com.app.mvc.exception.ParaException;
import com.app.mvc.util.BindingResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

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
    public JsonData save(@Valid ConfigurationParam param, BindingResult bindingResult) {

        if (bindingResult != null && bindingResult.hasErrors()) {
            log.error("参数错误, 错误信息:{}，参数:{}", JsonMapper.obj2String(BindingResultUtil.getErrors(bindingResult)), JsonMapper.obj2String(param));
            throw new ParaException(JsonMapper.obj2String(BindingResultUtil.getErrors(bindingResult)));
        }
        Configuration configuration = configurationService.saveOrUpdate(param);
        return JsonData.success(configuration);
    }

    @ResponseBody
    @RequestMapping("/page.json")
    public JsonData page(@Valid PageQuery page, BindingResult bindingResult) {
        if (bindingResult != null && bindingResult.hasErrors()) {
            log.error("参数错误, 错误信息:{}，参数:{}", JsonMapper.obj2String(BindingResultUtil.getErrors(bindingResult)), JsonMapper.obj2String(page));
            throw new ParaException(JsonMapper.obj2String(BindingResultUtil.getErrors(bindingResult)));
        }
        List<Configuration> configurationList = configurationService.getByPage(page);
        int count = configurationService.count();
        return JsonData.success(PageResult.<Configuration>builder().total(count).data(configurationList).build());
    }
}
