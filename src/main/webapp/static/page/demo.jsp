<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
    <title>上传视频</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<html>
<body>
<div class="panel panel-default">
    <div class="panel-body">
        <div class="panel-heading" align="center"><h1 class="sub-header h3">文件上传</h1></div>
        <hr>
        <form class="form-horizontal" id="upload" method="post" action="uploadflv/upload.do" enctype="multipart/form-data">
            <div class="form-group" align="center">
                <div class="col-md-4 col-sm-4  col-xs-4 col-lg-4">文件上传
                    <input type="file" class="form-control" name="file" id="file"><br>
                    <input type="submit" value="上传">
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>