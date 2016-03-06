package com.app.mvc.acl.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@Setter
public class AclPara {

    private Integer id;

    @Length(min = 2, max = 64, message = "权限名称长度需要在2~64个字之间")
    private String name;

    @NotNull(message = "必须选择权限模块")
    private Integer aclModuleId;

    @Length(min = 0, max = 128, message = "URL长度需要在128个字符以内")
    private String url;

    @NotNull(message = "必须指定类型")
    @Min(value = 0, message = "类型不合法")
    @Max(value = 2, message = "类型不合法")
    private Integer type;

    @NotNull(message = "必须指定状态")
    @Min(value = 0, message = "状态不合法")
    @Max(value = 1, message = "状态不合法")
    private Integer status;

    @NotNull(message = "展示顺序不能为空")
    private Integer seq;

    @Length(min = 0, max = 64, message = "备注长度需要在64个字以内")
    private String remark;

}
