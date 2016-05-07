package com.app.mvc.business.controller;

import com.app.mvc.acl.util.LoginUtil;
import com.app.mvc.beans.JsonData;
import com.app.mvc.beans.JsonMapper;
import com.app.mvc.business.bo.FileUploadBo;
import com.app.mvc.business.domain.FileInfo;
import com.app.mvc.business.service.FileInfoService;
import com.app.mvc.config.GlobalConfig;
import com.app.mvc.config.GlobalConfigKey;
import com.app.mvc.exception.NotFoundException;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin/file")
public class FileController {

    @Resource
    private FileInfoService fileInfoService;

    /**
     * 文件上传
     * 要求：前端上传文件的input框name属性必须为file，如果多个使用多个name="file"一起提交
     *
     * @param request
     * @return 实际图片的地址
     */
    @RequestMapping("/upload.json")
    public JsonData upload(HttpServletRequest request) {

        String operator = LoginUtil.getUserNameCookie();
        try {
            List<FileUploadBo> uploadFileList = Lists.newArrayList();

            //创建一个通用的多部分解析器
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

            //判断 request 是否有文件上传,即多部分请求
            if (multipartResolver.isMultipart(request)) {

                //转换成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

                MultiValueMap<String, MultipartFile> multiValueMap = multiRequest.getMultiFileMap();
                List<MultipartFile> fileList = multiValueMap.get("file");

                uploadFileList = fileInfoService.handleUploadFiles(fileList, operator);
            }
            log.info("文件上传结果:{}", JsonMapper.obj2String(uploadFileList));
            return JsonData.success(uploadFileList);

        } catch (Throwable e) {
            return JsonData.error("文件上传失败");
        }
    }

    @RequestMapping("/download.do")
    public void download(HttpServletRequest request, HttpServletResponse response, int id) throws Exception {

        FileInfo fileInfo = fileInfoService.findById(id);
        if (fileInfo == null) {
            throw new NotFoundException("未查到可下载的文件");
        }

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        String downloadPath = GlobalConfig.getValue(GlobalConfigKey.FILE_UPLOAD_PATH) + fileInfo.getName();

        long fileLength = new File(downloadPath).length();
        response.setHeader("Content-disposition", "attachment; filename=" + new String(fileInfo.getOriginName().getBytes("utf-8"), "ISO8859-1"));
        response.setHeader("Content-Length", String.valueOf(fileLength));

        bis = new BufferedInputStream(new FileInputStream(downloadPath));
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
        bis.close();
        bos.close();
    }
}

