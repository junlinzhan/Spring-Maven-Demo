package com.app.mvc.acl.domain;

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
public class SysRole {

    private Integer id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 状态, 备用
     *
     * @see com.app.mvc.acl.enums.Status
     */
    private int status;
    /**
     * 所属供应商
     */
    private int supplierId;
    /**
     * 角色类型, 备用
     *
     * @see com.app.mvc.acl.enums.RoleType
     */
    private int type;
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
