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
public class SysUser {

    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码(加密后)
     */
    private String password;
    /**
     * 电话
     */
    private String telephone;
    /**
     * 邮箱
     */
    private String mail;
    /**
     * 描述
     */
    private String remark;
    /**
     * 所属部门
     */
    private int deptId;
    /**
     * 状态
     *
     * @see com.app.mvc.acl.enums.Status
     */
    private int status;
    /**
     * 所属供应商
     */
    private int supplierId;
    /**
     * 管理的供应商
     */
    private String managedSupplierIds;
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
