package com.app.mvc.acl.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@Setter
public class UserPara {

    private Integer id;

    @Length(min = 0, max = 64, message = "用户名长度需要在64个字以内")
    private String username;

    @Length(min = 0, max = 32, message = "电话长度需要在32个字以内")
    private String telephone;

    @Length(min = 0, max = 32, message = "mail长度需要在32个字以内")
    @Email(message = "mail不允许为空")
    private String mail;

    private Integer supplierId;

    private String managedSupplierIds;

    @NotNull
    private Integer deptId;

    /**
     * @see com.app.mvc.acl.enums.Status
     */
    @Min(-1)
    @Max(2)
    private Integer status;
    private String remark;

}
