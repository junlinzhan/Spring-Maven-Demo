package com.app.mvc.config;

import com.app.mvc.acl.util.RequestHolder;
import com.app.mvc.beans.JsonData;
import com.app.mvc.beans.PageQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    @RequestMapping("/page.do")
    public ModelAndView page() {
        return new ModelAndView("config");
    }

    @ResponseBody
    @RequestMapping("/reloadAll.json")
    public JsonData reloadAll() {
        log.info("reload all machine config, username:{}", RequestHolder.getCurrentUser().getUsername());
        return JsonData.success(GlobalConfig.loadMachineConfig());
    }

    @ResponseBody
    @RequestMapping("/reload.json")
    public JsonData reload() {
        log.info("reload config, username:{}", RequestHolder.getCurrentUser().getUsername());
        GlobalConfig.loadAllConfig();
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
