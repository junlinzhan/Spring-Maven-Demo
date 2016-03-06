package com.app.mvc.acl.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@ToString
@Getter
@Setter
public class DeptPara {

    private Integer id;

    @Length(min = 2, max = 25, message = "部门名称长度需要在2~25个字之间")
    private String name;

    @Length(min = 0, max = 64, message = "备注长度需要在64个字以内")
    private String remark;

    private Integer parentId;

    @NotNull(message = "展示顺序不可以为空")
    private Integer seq;

    private Integer supplierId;

}
