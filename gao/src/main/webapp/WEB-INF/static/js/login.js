$(document).ready(
    function () {
        $("#Submitbtn").click(
            function () {
                if ($("#name").val() == "") {
                    noty({
                        text: '用户名不能为空!',
                        layout: 'center',
                        type: 'alert',
                        theme: 'relax',
                        timeout: 1000
                    });
                    return;
                }
                if ($("#password").val() == "") {
                    noty({
                        text: '密码不能为空!',
                        layout: 'center',
                        type: 'alert',
                        theme: 'relax',
                        timeout: 1000
                    });
                    return;
                }
                var params = $("form").serialize();
                $.ajax({
                    url: "checklogin",
                    type: "POST",
                    data: params,
                    dataType: "json",
                    success: function (data) {
                        $("#message").html("");
                        if (data.islogin == "true") {
                            location.href="index";
                        } else {
                            $("#name").val("");
                            $("#password").val("");
                            noty({
                                text: '登陆失败!',
                                layout: 'center',
                                type: 'alert',
                                theme: 'relax',
                                timeout: 1000
                            });
                        }
                    },
                    error: function () {
                        alert("error!");
                    }
                });
            });
    });