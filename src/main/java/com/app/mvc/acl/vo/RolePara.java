package com.app.mvc.acl.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@ToString
@Getter
@Setter
public class RolePara {

    private Integer id;

    @Length(min = 4, max = 32, message = "角色名称长度需要在4~32个字之间")
    private String name;

    @Length(min = 0, max = 64, message = "备注长度需要在64个字以内")
    private String remark;

    private int supplierId = 0;

    /**
     * @see com.app.mvc.acl.enums.Status
     */
    @Min(0)
    @Max(1)
    private int status = 1;

    /**
     * @see com.app.mvc.acl.enums.RoleType
     */
    @Min(0)
    @Max(2)
    private int type = 0;

}
