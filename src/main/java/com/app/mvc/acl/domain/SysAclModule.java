package com.app.mvc.acl.domain;

import com.app.mvc.acl.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by jimin on 16/1/16.
 */

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysAclModule {

    private Integer id;
    /**
     * 权限模块名称
     */
    private String name;
    /**
     * 父权限模块
     */
    private Integer parentId;
    /**
     * 权限模块层级
     */
    private String level;
    /**
     * 权限模块状态
     *
     * @see com.app.mvc.acl.enums.Status
     */
    private int status = Status.AVAILABLE.getCode();
    /**
     * 权限模块在当前层级的序号
     */
    private int seq;
    /**
     * 备注
     */
    private String remark;
    /**
     * 操作者
     */
    private String operator;
    /**
     * 操作者ip
     */
    private String operateIp;
    /**
     * 操作时间
     */
    private Date operateTime;
}
