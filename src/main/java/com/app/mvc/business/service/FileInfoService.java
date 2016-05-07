package com.app.mvc.business.service;

import com.app.mvc.beans.JsonMapper;
import com.app.mvc.business.bo.FileUploadBo;
import com.app.mvc.business.dao.FileInfoDao;
import com.app.mvc.business.domain.FileInfo;
import com.app.mvc.common.ThreadPool;
import com.app.mvc.config.GlobalConfig;
import com.app.mvc.config.GlobalConfigKey;
import com.app.mvc.util.DateTimeUtil;
import com.app.mvc.util.FileMD5Util;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.Random;

/**
 * Created by jimin on 15/11/29.
 */
@Slf4j
@Service
public class FileInfoService {

    @Resource
    private FileInfoDao fileInfoDao;

    public FileInfo findById(int id) {
        return fileInfoDao.findById(id);
    }

    public List<FileUploadBo> handleUploadFiles(List<MultipartFile> fileList, String operator) throws Exception {
        List<FileUploadBo> uploadFileList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(fileList)) {
            return uploadFileList;
        }

        for (MultipartFile file : fileList) {
            //记录上传过程起始时的时间，用来计算上传时间
            int start = (int) System.currentTimeMillis();

            String fileMD5 = "";

            if (file != null) {

                //取得当前上传文件的文件名称
                String originalFilename = file.getOriginalFilename();

                //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                if (originalFilename.trim() != "") {

                    // 判断文件是否已经上传过
                    fileMD5 = FileMD5Util.getMD5String(file.getBytes());
                    FileInfo fileInfo = fileInfoDao.findByMD5(fileMD5);
                    if (fileInfo != null) {
                        log.info("{}已经上传过，md5:{},file:{}", originalFilename, fileMD5, JsonMapper.obj2String(fileInfo));
                        uploadFileList.add(FileUploadBo.success(originalFilename, fileInfo.getName()));
                        continue;
                    }

                    //重命名上传后的文件名
                    String fileName = generateFilePrefix() + getSuffix(originalFilename);

                    //定义上传路径
                    String path = GlobalConfig.getValue(GlobalConfigKey.FILE_UPLOAD_PATH) + fileName;
                    File localFile = new File(path);
                    file.transferTo(localFile);

                    // 把文件上传信息保存到数据库中
                    fileInfo = new FileInfo(originalFilename, fileName, operator, fileMD5, file.getSize());
                    asyncSaveFileInfo(fileInfo);
                    uploadFileList.add(FileUploadBo.success(originalFilename, fileName));
                }
                //记录上传该文件后的时间
                long time = System.currentTimeMillis() - start;
                log.info("upload {} use time {}, size: {}, md5: {}", originalFilename, time, file.getSize(), fileMD5);
            }
        }
        return uploadFileList;
    }

    private String generateFilePrefix() {
        // yyyyMMddHHmmss + 1位随机数
        return DateTimeUtil.allFrom(DateTime.now()) + "" + new Random().nextInt(10);
    }

    private String getSuffix(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index == -1) {
            return ""; // 无后缀
        }
        return fileName.substring(index);
    }

    private void asyncSaveFileInfo(final FileInfo fileInfo) {
        ThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    fileInfoDao.insert(fileInfo);
                } catch (Throwable e) {
                    log.error("async insert file info error, {}", JsonMapper.obj2String(fileInfo), e);
                }
            }
        });
    }
}
