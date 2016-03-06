<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <jsp:include page="common/header.jsp"/>
    <script language="javascript">
        function CheckForm() {
            if (document.add.name.value == "") {
                alert("对不起,请输入您的姓名！");
                document.add.name.focus();
                return false;
            }
            if (document.add.mobile.value == "") {
                alert("对不起,请输入您的联系电话！");
                document.add.mobile.focus();
                return false;
            }

            if (document.add.qq.value == "") {
                alert("对不起,请输入您的qq！");
                document.add.qq.focus();
                return false;
            }

            if (document.add.code.value == "") {
                alert("对不起,请输入验证码！");
                document.add.code.focus();
                return false;
            }
        }
    </script>
</head>
<body>
<jsp:include page="common/content.jsp"/>

<div class="nyfff">
    <div class="ny_content">

        <div class="ny_ab_left">
            <div class="ny_ab_left_bt">
                <div class="ab_tit"><img src="images/jmit.gif" width=869 height=43></div>
                <div class="ab_lo">
                    <div class="ab_tit_left">在线提交</div>
                    <div class="ab_tit_right_k">
                        <div class="ab_tit_right">当前位置：<a href="index.jsp">首页</a> &gt;&gt; <a href="jion.jsp">招商加盟</a>
                            &gt;&gt; <span>在线提交</span></div>
                        <div class="ab_tit_right_l"><img src="images/fk.gif" width="28" height="24"></div>
                    </div>
                </div>

                <div class="ab_bot" style="font-size:14px;">

                    <form name="add" method="post" action="?action=add" onSubmit="return CheckForm();"
                          enctype="multipart/form-data">
                        <table width="100%" height="448" border="0" cellspacing="1" class="tableapply">
                            <tr>
                                <td width="99" class="td">您的姓名：</td>
                                <td width="301"><input name="name" type="text" class="input2" size="20"><span>*</span>
                                </td>
                                <td width="112" class="td">手机号码：</td>
                                <td width="266"><input class="input2" name="mobile" type="text"/><span>*</span></td>
                            </tr>
                            <tr>
                                <td class="td">座机号码：</td>
                                <td><input class="input2" name="tel" type="text"/></td>
                                <td class="td">传真号码：</td>
                                <td><input class="input2" name="fax" type="text"/></td>
                            </tr>

                            <tr>
                                <td width="99" class="td">所在地区：</td>
                                <td width="301"><select class="select" name="jiguan">
                                    <option selected=""
                                            value="北京">北京
                                    </option>
                                    <option value="福建">福建</option>
                                    <option
                                            value="上海">上海
                                    </option>
                                    <option value="天津">天津</option>
                                    <option
                                            value="重庆">重庆
                                    </option>
                                    <option value="浙江">浙江</option>
                                    <option
                                            value="广州">广州
                                    </option>
                                    <option value="河北">河北</option>
                                    <option
                                            value="山西">山西
                                    </option>
                                    <option value="内蒙">内蒙</option>
                                    <option
                                            value="辽宁">辽宁
                                    </option>
                                    <option value="吉林">吉林</option>
                                    <option
                                            value="黑龙">黑龙
                                    </option>
                                    <option value="江苏">江苏</option>
                                    <option
                                            value="浙江">浙江
                                    </option>
                                    <option value="安徽">安徽</option>
                                    <option
                                            value="江西">江西
                                    </option>
                                    <option value="山东">山东</option>
                                    <option
                                            value="河南">河南
                                    </option>
                                    <option value="湖北">湖北</option>
                                    <option
                                            value="湖南">湖南
                                    </option>
                                    <option value="广东">广东</option>
                                    <option
                                            value="广西">广西
                                    </option>
                                    <option value="海南">海南</option>
                                    <option
                                            value="四川">四川
                                    </option>
                                    <option value="贵州">贵州</option>
                                    <option
                                            value="云南">云南
                                    </option>
                                    <option value="西藏">西藏</option>
                                    <option
                                            value="陕西">陕西
                                    </option>
                                    <option value="甘肃">甘肃</option>
                                    <option
                                            value="青海">青海
                                    </option>
                                    <option value="宁夏">宁夏</option>
                                    <option
                                            value="新疆">新疆
                                    </option>
                                    <option value="台湾">台湾</option>
                                    <option
                                            value="香港">香港
                                    </option>
                                    <option value="其它">其它</option>
                                </select></td>
                                <td width="112" class="td">性　　别：</td>
                                <td width="266"><select class="select" name="sex">
                                    <option value="男">男</option>
                                    <option value="女">女</option>
                                </select></td>
                            </tr>


                            <tr>
                                <td width="99" class="td">E&nbsp;-&nbsp;Mail：</td>
                                <td width="301"><input class="input2" name="email" type="text"/></td>
                                <td width="112" class="td">QQ 号：</td>
                                <td width="266"><input class="input2" name="qq" type="text"/><span>*</span></td>
                            </tr>

                            <tr>
                                <td width="99" class="td">经营品牌：</td>
                                <td colspan="3"><input class="input2" name="jypp" type="text"/></td>
                            </tr>

                            <tr>
                                <td class="td">经营模式：</td>
                                <td colspan="3" class="td" style="text-align:left">
                                    <input name="jyms[]" type="checkbox" value="自营"/> 自营&nbsp;&nbsp;&nbsp;
                                    <input name="jyms[]" type="checkbox" value="合伙"/> 合伙&nbsp;&nbsp;&nbsp;
                                    <input name="jyms[]" type="checkbox" value="家族"/> 家族&nbsp;&nbsp;&nbsp;
                                    <input name="jyms[]" type="checkbox" value="其他"/> 其他

                                </td>
                            </tr>
                            <tr>
                                <td class="td">场馆名称：</td>
                                <td><input class="input2" name="cgmc" type="text"/></td>
                                <td class="td">经营面积：</td>
                                <td><input class="input2" name="cgmj" type="text"/></td>
                            </tr>


                            <tr>
                                <td class="td">合约时间：</td>
                                <td><input class="input2" name="hysj" type="text"/></td>
                                <td class="td">投资金额：</td>
                                <td><input class="input2" name="tzje" type="text"/></td>
                            </tr>
                            <tr>
                                <td class="td"> 备　注：</td>
                                <td colspan="3"><textarea class="textarea" name="note"></textarea></td>
                            </tr>
                            <tr>
                                <td class="td">验证码：</td>
                                <td><input class="input80" name="code" type="text"><img style="cursor:pointer;"
                                                                                        onClick="this.src='authcode.php'"
                                                                                        alt="验证码,看不清楚?请点击刷新验证码"
                                                                                        src="authcode.php"
                                                                                        align="absmiddle"><span>*</span>
                                </td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td class="td"></td>
                                <td><input class="sub1" name="submit" value="立即提交" type="submit"></td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                            </tr>


                        </table>
                    </form>


                    <div class="c"></div>
                </div>
            </div>
            <div class="ab_dot"><img src="images/dot.gif" width="869" height="5"></div>
        </div>

        <div class="ny_ab_right">
            <ul>
                <li><a href="jion.jsp">加盟流程</a></li>
                <li><a class=cu href="tijiao.jsp">在线提交</a></li>
            </ul>
        </div>
        <div class="c"></div>
    </div>
</div>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
