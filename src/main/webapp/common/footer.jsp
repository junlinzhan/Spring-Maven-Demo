<%@ page import="com.app.mvc.acl.util.LoginUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="foot">
    <div class="foot_mid">
        <div class="foot_mid_head"><a href="index.jsp">首页</a> | <a href="about.jsp">关于XX</a> | <a
                href="news.jsp">新闻中心</a> | <a href="products.jsp">产品中心</a> | <a href="tech.jsp">XX工艺</a> | <a
                href="service.jsp">特色服务</a> | <a href="jion.jsp">招商加盟</a> | <a href="contact.jsp">联系我们</a>
            <div class="foot_mid_head_right">
                <% if(!LoginUtil.getUserFromCookie(request, response).isRet()){ %> <a href="signin.jsp">登 陆</a> <% } else {%> <a href="/admin/page.do">管 理</a> <%}%>
                &nbsp;&nbsp;&nbsp;百 年 X X &nbsp;&nbsp;&nbsp;锻 木 成 金
            </div>
        </div>
        <div class="bt">
            <div class="btleft">
                版权：北京XX家具有限公司 All Right Reserved <br>
                电话： 010-xxxxxxxx/xxxxxxxx　招商专线：186-1240-3296 <br>
                地址：北京市海淀区xxx <br>
                <a href="http://www.miitbeian.gov.cn/" target="_blank">ICP备案编号：京ICP备1234567890号</a>　技术支持：<a
                    href="http://www.conove.com/" target="_blank">科诺威</a></div>
            <div class="btright">
                <div class="btright_left">
                    <div class="btright_left_img"><img src="images/rw1.jpg" width="90" height="90"></div>
                    <div class="btright_left_chaw">
                        <p>请扫描左侧二维码</p>

                        <p>关注XX家具微信</p>
                    </div>
                </div>
                <div class="btright_right">
                    <div class="btright_right_img"><img src="images/rw2.jpg" width="90" height="90"></div>
                    <div class="btright_right_chw">手机扫二维码关注官方微博<br>点击“<a href="http://weibo.com/kanwangzjm"
                                                                         target="_blank">XX家具</a>”直接进入
                    </div>
                </div>

            </div>
        </div>

    </div>
</div>
<SCRIPT type=text/javascript src="js/kefu.js"></SCRIPT>
<DIV id=floatTools class=float0831>
    <DIV class=floatL>

        <A style="DISPLAY: none" id=aFloatTools_Show class=btnOpen title=查看在线客服
           onclick="javascript:$('#divFloatToolsView').animate({width: 'show', opacity: 'show'}, 'normal',function(){ $('#divFloatToolsView').show();kf_setCookie('RightFloatShown', 0, '', '/', 'www.istudy.com.cn'); });$('#aFloatTools_Show').attr('style','display:none');$('#aFloatTools_Hide').attr('style','display:block');"
           href="javascript:void(0);">展开</A>

        <A id=aFloatTools_Hide class=btnCtn btnOpen title=关闭在线客服
           onclick="javascript:$('#divFloatToolsView').animate({width: 'hide', opacity: 'hide'}, 'normal',function(){ $('#divFloatToolsView').hide();kf_setCookie('RightFloatShown', 1, '', '/', 'www.istudy.com.cn'); });$('#aFloatTools_Show').attr('style','display:block');$('#aFloatTools_Hide').attr('style','display:none');"
           href="javascript:void(0);">收缩</A>

    </DIV>
    <DIV id=divFloatToolsView class=floatR>
        <DIV class=tp></DIV>
        <DIV class=cn>
            <UL>
                <LI class=top>
                    <H3 class=titZx>QQ咨询</H3>
                </LI>
                <LI><a class=icoTc target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=466420182&site=qq&menu=yes">售前咨询</a>
                </LI>
                <LI><a class=icoTc target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=466420182&site=qq&menu=yes">售后服务</a>
                </LI>
                <LI class=bot><a class=icoTc target="_blank"
                                 href="http://wpa.qq.com/msgrd?v=3&uin=466420182&site=qq&menu=yes">招商加盟</a></LI>
            </UL>
            <UL class=webZx>
                <LI class=webZx-in><A href="tijiao.jsp" target="_blank" style="FLOAT: left"><IMG
                        src="images/right_float_web.png" border="0px"></A></LI>
            </UL>
            <UL>
                <LI>
                    <H3 class=titDh>电话咨询</H3>
                </LI>
                <LI><SPAN class=icoTl>186-1240-3296</SPAN></LI>
            </UL>
        </DIV>
    </DIV>
</DIV>
