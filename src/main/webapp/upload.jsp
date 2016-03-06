<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Upload</title>
    <script type="text/javascript">
        var i = 1;
        $(document).ready(function () {
            $("#btn_add").click(function () {
                document.getElementById("newUpload").innerHTML += '<div id="div_' + i + '"><input  name="file" type="file"  /><input type="button" value="删除"  onclick="del(' + i + ')"/></div>';
                i = i + 1;
            });
        });

        function del(o) {
            document.getElementById("newUpload").removeChild(document.getElementById("div_" + o));
        }

    </script>
</head>
<body>

<h1>springMVC包装类上传文件</h1>

<form name="userForm" action="/admin/file/upload.json" enctype="multipart/form-data" method="post">
<div id="newUpload">
    <input type="file" name="file">
</div>
<input type="button" id="btn_add" value="增加一行">
<input type="submit" value="上传">


</form>
</body>
</html>