package com.app.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Title: DetailController.java
 * Description:
 *
 * @author jimin.zheng
 * @date 15-10-27
 */
@Controller
public class DetailController {

    @ResponseBody
    @RequestMapping(value = "detail.json", method = RequestMethod.GET)
    public ModelAndView printWelcome(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("ret", true);
        mav.addObject("data", "success");
        return mav;
    }
}
