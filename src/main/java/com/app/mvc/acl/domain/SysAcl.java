package com.app.mvc.acl.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(of = { "id" })
public class SysAcl {

    private Integer id;
    /**
     * 权限点标识
     */
    private String code;
    /**
     * 权限点名称
     */
    private String name;
    /**
     * 权限点所属权限模块
     */
    private int aclModuleId;
    /**
     * 权限点对应url
     */
    private String url;
    /**
     * 权限点类型
     *
     * @see com.app.mvc.acl.enums.AclType
     */
    private int type;
    /**
     * 权限点状态
     *
     * @see com.app.mvc.acl.enums.Status
     */
    private int status;
    /**
     * 权限点在当前权限模块的序号
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
