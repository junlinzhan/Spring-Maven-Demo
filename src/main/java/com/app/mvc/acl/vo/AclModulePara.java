package com.app.mvc.acl.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@ToString
@Getter
@Setter
public class AclModulePara {

    private Integer id;

    @Length(min = 2, max = 64, message = "权限模块名称长度需要在2~64个字之间")
    private String name;

    @NotNull(message = "权限模块展示顺序不能为空")
    private Integer seq;

    @Length(min = 0, max = 64, message = "备注长度需要在64个字以内")
    private String remark;

    @NotNull(message = "权限模块状态不能为空")
    private int status;

    private Integer parentId;

}
