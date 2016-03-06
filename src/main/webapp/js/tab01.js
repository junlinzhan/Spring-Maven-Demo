function Tab(btn, btnid, btnclass, con, conclass) {
    var self = this;
    var dq;
    self.init = function () {
        btn.bind("click", self.TheClick)
        btn.hover(
            function () {
                if ($(this).attr("id") != btnid) {
                    $(this).attr("class", btnclass);
                }

            },
            function () {
                if ($(this).attr("id") != btnid) {
                    $(this).attr("class", btnclass + "01");
                }
            });
    }
    self.TheClick = function () {
        dq = btn.index(this);
        $("." + btnclass).attr("class", btnclass + "01");
        $(this).attr("class", btnclass);
        $("#" + btnid).removeAttr("id");
        $(this).attr("id", btnid);
        $("." + conclass).attr("class", conclass + "01");
        con.eq(dq).attr("class", conclass);
    }


}


$(function () {
    var TheBtn = $(".tab_ul li"), TheId = "TheDq", TheBtnClass = "tab_title_li", TheCon = $(".con li"), TheConClass = "con_li";
    TheTab = new Tab(TheBtn, TheId, TheBtnClass, TheCon, TheConClass);
    TheTab.init();


    var TheBtn01 = $(".TheTab_ul li"), TheId01 = "TheTheTabDq", TheBtnClass01 = "TheTab_title_li", TheCon01 = $(".TheCon li"), TheConClass01 = "TheCon_li";
    TheTab01 = new Tab(TheBtn01, TheId01, TheBtnClass01, TheCon01, TheConClass01);
    TheTab01.init();


})