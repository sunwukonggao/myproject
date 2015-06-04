$('#upload-button').uploadify({
    'width': '265',
    'height': '65',
    'buttonText': '上传图片',
    'auto': false, //不自动提交
    'swf': 'static/js//uploadify.swf',                      //falsh上传图片
    'uploader': 'uploadfile.action',                                //上传处理，连接后台
    'fileTypeDesc': 'Supported File Format',     //文件类型描述
    'fileTypeExts': '*.jpg;*.jpge;*.gif;*.png',     //上传文件类型
    'fileSizeLimit': '10MB', //文件最大大小
    'fileObjName': 'file',  //后台接受文件对象名，保持一致
    'formData': {'id': 1, 'name': 'irwin'}, //测试附加数据
    'onSelectError': function (file, errorCode, errorMsg) { //file选择失败后触发
        alert(errorMsg);
    },
    'onFallback': function () { //flash报错触发
        alert("请您先安装flash控件");
    },
    'onUploadSuccess': function (file, data, response) { //上传成功后触发
        if ("sizeError" == data) {
            alert("文件大小不能超过10M");
        } else if ("typeError" == data) {
            alert("不支持的文件类型");
        }
    }
});