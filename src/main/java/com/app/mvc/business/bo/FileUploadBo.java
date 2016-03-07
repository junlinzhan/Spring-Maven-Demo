package com.app.mvc.business.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by jimin on 15/11/29.
 */
@Getter
@Setter
@ToString
public class FileUploadBo {

    private String originFileName;

    private String pathName;

    private boolean ret;

    private String errMsg;

    public FileUploadBo() {
    }

    public FileUploadBo(String originFileName, String pathName, boolean ret, String errMsg) {
        this.originFileName = originFileName;
        this.pathName = pathName;
        this.ret = ret;
        this.errMsg = errMsg;
    }

    public static FileUploadBo success(String originFileName, String pathName) {
        return new FileUploadBo(originFileName, pathName, true, "");
    }

    public static FileUploadBo failed(String originFileName, String errMsg) {
        return new FileUploadBo(originFileName, "", false, errMsg);
    }

}
