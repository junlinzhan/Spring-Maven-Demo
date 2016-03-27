<div class="tophead">
    <div class="tophead_mid">
        <div class="logo"><a href="index.jsp"><img src="images/logo.jpg" width="190" height="79"></a></div>
        <div class="menu">
            <div class="a1"><a id="menu1" href="index.jsp"></a></div>
            <div class="a2"><a id="menu2" href="about.jsp"></a></div>
            <div class="a3"><a id="menu3" href="news.jsp"></a></div>
            <div class="a4"><a id="menu4" href="products.jsp"></a></div>
            <div class="a5"><a id="menu5" href="tech.jsp"></a></div>
            <div class="a6"><a id="menu6" href="service.jsp"></a></div>
            <div class="a7"><a id="menu7" href="jion.jsp"></a></div>
            <div class="a8"><a id="menu8" href="contact.jsp"></a></div>
        </div>
    </div>
</div>
<%
    String href = request.getRequestURI();
    int from;
    if (href.indexOf("index") >= 0) {
        from = 1;
    } else if (href.indexOf("about") >= 0) {
        from = 2;
    } else if (href.indexOf("news") >= 0 || href.indexOf("detail") >= 0) {
        from = 3;
    } else if (href.indexOf("product") >= 0) {
        from = 4;
    } else if (href.indexOf("tech") >= 0) {
        from = 5;
    } else if (href.indexOf("service") >= 0) {
        from = 6;
    } else if (href.indexOf("jion") >= 0) {
        from = 7;
    } else if (href.indexOf("contact") >= 0) {
        from = 8;
    } else {
        from = 1;
    }
%>
<script type="application/javascript">
    var index = '<%=from %>';
    $("#menu" + index).attr("class", "cu");
</script>