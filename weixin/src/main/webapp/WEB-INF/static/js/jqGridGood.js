$(function () {
    // 配置jqGrid组件
    $("#gridTable").jqGrid({
        url: "allgood",
        datatype: "json",
        mtype: "POST",
        height: "auto",
        width: 1024,
        colModel: [{
            name: "id",
            index: "id",
            label: "id",
            width: 60,
            searchoptions: {
                sopt: ["eq", "lt", "le", "gt", "ge"]
            }

        }, {
            name: "good_id",
            index: "good_id",
            label: "商品id",
            width: 80,
            searchoptions: {
                sopt: ["eq", "lt", "le", "gt", "ge", "cn"]
            }

        }, {
            name: "good_name",
            index: "good_name",
            label: "商品名称",
            width: 80,
            searchoptions: {
                sopt: ["eq", "lt", "le", "gt", "ge", "cn"]
            }

        },],
        viewrecords: true,
        rowNum: 20,
        rowList: [20, 50, 100],
        prmNames: {
            search: "search"
        },
        jsonReader: {
            root: "allrow",
            records: "record",
            repeatitems: false
        },
        pager: "#gridPager",
        multiselect: true,
        caption: "商品列表",
        hidegrid: false
    });
    var alertText = "<div style='margin:0.3em; background:#FFFFFF; border:1px solid #A6C9E2; padding: 0.5em;'>请选择需要操作的数据行!</div>";

    $("#gridTable").jqGrid("navGrid", "#gridPager", {
        addfunc: openDialogAdding, // (1) 点击添加按钮，则调用openDialog4Adding方法
        editfunc: openDialogUpdating, // (2) 点击添加按钮，则调用openDialog4Updating方法
        delfunc: openDialogDeleting, // (3) 点击添加按钮，则调用openDialog4Deleting方法
        alerttext: alertText
        // (4) 当未选中任何行而点击编辑、删除、查看按钮时，弹出的提示信息
    }, {}, {}, {}, { // (5) 修改于查询相关的prmSearch参数
        caption: "查找",
        Find: "go!",
        multipleSearch: true,
        closeAfterSearch: true,
        groupOps: [{
            op: "AND",
            text: "全部"
        }],
    }, {});

    // 配置对话框
    $("#consoleDlg").dialog({
        autoOpen: false,
        modal: true, // 设置对话框为模态（modal）对话框
        resizable: true,
        width: 480,
        buttons: { // 为对话框添加按钮
            "取消": function () {
                $("#consoleDlg").dialog("close");
            },
            "添加": addUser,
            "保存": updateUser,
        }
    });

    $("#consoleDlgdel").dialog({
        autoOpen: false,
        modal: true, // 设置对话框为模态（modal）对话框
        resizable: true,
        width: 480,
        buttons: { // 为对话框添加按钮
            "取消": function () {
                $("#consoleDlgdel").dialog("close");
            },
            "删除": deleteUser,
        }
    });
});
var openDialogAdding = function () {
    var consoleDlg = $("#consoleDlg");
    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");

    consoleDlg.find("input").removeAttr("disabled").val("");
    dialogButtonPanel.find("button:not(:contains('取消'))").hide();
    dialogButtonPanel.find("button:contains('添加')").show();
    consoleDlg.dialog("option", "title", "添加用户");
    loadSelectedRowDatapt();
};
var openDialogUpdating = function (rowid) {
    var consoleDlg = $("#consoleDlg");
    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");

    consoleDlg.find("input").removeAttr("disabled");
    dialogButtonPanel.find("button:not(:contains('取消'))").hide();
    dialogButtonPanel.find("button:contains('保存')").show();
    consoleDlg.dialog("option", "title", "修改用户");

    loadSelectedRowData(rowid);
};
var openDialogDeleting = function () {
    var consoleDlg = $("#consoleDlgdel");
    var dialogButtonPanel = consoleDlg.siblings(".ui-dialog-buttonpane");

    consoleDlg.find("input").removeAttr("disabled");
    dialogButtonPanel.find("button:not(:contains('取消'))").hide();
    dialogButtonPanel.find("button:contains('删除')").show();
    consoleDlg.dialog("option", "title", "删除用户");
    consoleDlg.dialog("open");
};
var loadSelectedRowData = function (selectedRowId) {
    var str = "";
    var strse = "";
    var strs = "";
    var strp = "";
    var params = {
        "uservog.id": selectedRowId
    };
    var actionUrl = "acafindUserAllByid.action";
    // 从Server读取对应ID的JSON数据
    $
        .ajax({
            url: actionUrl,
            data: params,
            dataType: "json",
            cache: false,
            error: function (textStatus, errorThrown) {
                $.noty.consumeAlert({
                    layout: 'center',
                    type: 'error',
                    dismissQueue: true
                });
                alert("系统ajax交互错误: " + textStatus);
            },
            success: function (data, textStatus) {
                if (data.sessionstate == "timeout") {
                    $.noty.consumeAlert({
                        layout: 'center',
                        type: 'error',
                        dismissQueue: true
                    });
                    alert("您的session已过期，请重新登陆");
                    window.open('/bjtuteach/index.action', '_top');
                }
                else {// 如果读取结果成功，则将信息载入到对话框中
                    var rowData = data.uservog;
                    var gra = data.gr;
                    var pta = data.aca;
                    var i = gra.length;
                    var k = pta.length;
                    var consoleDlg = $("#consoleDlg");
                    consoleDlg.find("#selectId").val(rowData.id);
                    consoleDlg.find("#user_id").val(rowData.user_id);
                    consoleDlg.find("#name").val(rowData.name);
                    consoleDlg.find("#birth").val(UnixToDate(rowData.birth));
                    consoleDlg.find("#idcard").val(rowData.idcard);
                    for (var j = 0; j < i; j++) {
                        if (rowData.group_id == gra[j].group_name) {
                            str = str + "<option value=" + gra[j].id
                                + "  selected='selected'> "
                                + gra[j].group_name + "</option>";
                        } else {
                            str = str + "<option value=" + gra[j].id + "> "
                                + gra[j].group_name + "</option>";
                        }
                    }
                    consoleDlg.find("#group_id").html(str);
                    for (var j = 0; j < k; j++) {
                        if (rowData.academe_id == pta[j].academe_name) {
                            strp = strp + "<option value=" + pta[j].id
                                + "  selected='selected'> "
                                + pta[j].academe_name + "</option>";
                        } else {
                            strp = strp + "<option value=" + pta[j].id + "> "
                                + pta[j].academe_name + "</option>";
                        }
                    }
                    consoleDlg.find("#academe_id").html(strp);
                    if (rowData.state = "Y") {
                        strs = strs
                            + "<option value='Y'  selected='selected'> Y</option><option value='N'> N</option>";
                    } else {
                        strs = strs
                            + "<option value='N'  selected='selected'> N</option><option value='Y'>Y</option>";
                    }
                    if (rowData.sex = "男") {
                        strse = strse
                            + "<option value='男'  selected='selected'> 男</option><option value='女'> 女</option>";
                    } else {
                        strse = strse
                            + "<option value='女'  selected='selected'> 女</option><option value='男'> 男</option>";
                    }
                    consoleDlg.find("#state").html(strs);
                    consoleDlg.find("#sex").html(strse);
                    // 根据新载入的数据将表格中的对应数据行一并更新一下
                    var dataRow = {
                        id: rowData.id,
                        user_id: rowData.user_id,
                        name: rowData.name,
                        group_id: rowData.group_id,
                        academe_id: rowData.academe_id,
                        state: rowData.state,
                        birth: rowData.birth,
                        idcard: rowData.idcard,
                        sex: rowData.sex,
                    };
                    $("#gridTable").jqGrid("setRowData", data.uservog.id, dataRow);

                    // 打开对话框
                    consoleDlg.dialog("open");
                }
            }
        });
};
var loadSelectedRowDatapt = function () {
    var str = "";
    var strg = "";
    var actionUrl = "acafindAcademeAndGroupAll.action";
    // 从Server读取对应ID的JSON数据
    $
        .ajax({
            url: actionUrl,
            dataType: "json",
            cache: false,
            error: function (textStatus, errorThrown) {
                $.noty.consumeAlert({
                    layout: 'center',
                    type: 'error',
                    dismissQueue: true
                });
                alert("系统ajax交互错误: " + textStatus);
            },
            success: function (data, textStatus) {
                if (data.sessionstate == "timeout") {
                    $.noty.consumeAlert({
                        layout: 'center',
                        type: 'error',
                        dismissQueue: true
                    });
                    alert("您的session已过期，请重新登陆");
                    window.open('/bjtuteach/index.action', '_top');
                }
                else {
                    // 如果读取结果成功，则将信息载入到对话框中
                    var pta = data.aca;
                    var gra = data.gr;
                    var k = gra.length;
                    var i = pta.length;
                    var consoleDlg = $("#consoleDlg");
                    for (var j = 0; j < i; j++) {
                        str = str + "<option value=" + pta[j].id + "> "
                            + pta[j].academe_name + "</option>";
                    }
                    for (var j = 0; j < k; j++) {
                        strg = strg + "<option value=" + gra[j].id + "> "
                            + gra[j].group_name + "</option>";
                    }

                    consoleDlg
                        .find("#sex")
                        .html(
                        "<option value='男' >男</option><option value='女' >女</option>");
                    // consoleDlg.find("#birth").html("<img
                    // src='/bjtuteach/static/js/My97DatePicker/skin/datePicker.gif'
                    // width='16' height='22' align='absmiddle'
                    // style='cursor:pointer'
                    // onClick='WdatePicker({el:'aa'})'/>");
                    consoleDlg
                        .find("#state")
                        .html(
                        "<option value='Y' >Y</option><option value='N' >N</option>");
                    consoleDlg.find("#academe_id").html(str);
                    consoleDlg.find("#group_id").html(strg);

                    // 打开对话框
                    consoleDlg.dialog("open");
                }
            }
        });
};
var addUser = function () {
    var consoleDlg = $("#consoleDlg");
    var academe_name = $.trim($("#academe_id").find("option:selected").text());
    var statename = $.trim($("#state").find("option:selected").text());
    var sexname = $.trim($("#sex").find("option:selected").text());
    var group_name = $.trim($("#group_id").find("option:selected").text());
    var academe_id = $.trim(consoleDlg.find("#academe_id").val());
    var group_id = $.trim(consoleDlg.find("#group_id").val());
    var state = $.trim(consoleDlg.find("#state").val());
    var sex = $.trim(consoleDlg.find("#sex").val());
    var user_id = $.trim(consoleDlg.find("#user_id").val());
    var name = $.trim(consoleDlg.find("#name").val());
    var birth = $.trim(consoleDlg.find("#birth").val());
    var idcard = $.trim(consoleDlg.find("#idcard").val());
    var params = {
        "userg.user_id": user_id,
        "userg.name": name,
        "userg.group_id": group_id,
        "userg.academe_id": academe_id,
        "userg.state": state,
        "userg.birth": DateToUnix(birth),
        "userg.idcard": idcard,
        "userg.sex": sex,
    };
    if ($("#birth").val() == "") {
        $.noty.consumeAlert({
            layout: 'center',
            type: 'error',
            dismissQueue: true
        });
        alert("生日格式非法!");
    }

    var actionUrl = "acaaddUser.action";
    $
        .ajax({
            url: actionUrl,
            data: params,
            dataType: "json",
            cache: false,
            error: function (textStatus, errorThrown) {
                $.noty.consumeAlert({
                    layout: 'center',
                    type: 'error',
                    dismissQueue: true
                });
                alert("系统ajax交互错误: " + textStatus);
            },
            success: function (data, textStatus) {
                var member = eval("(" + data.result + ")");
                if (member != null) {
                    if (member.userid == "no") {
                        $.noty.consumeAlert({
                            layout: 'center',
                            type: 'error',
                            dismissQueue: true
                        });
                        alert("用户ID不合法!");
                    }
                    if (member.name == "no") {
                        $.noty.consumeAlert({
                            layout: 'center',
                            type: 'error',
                            dismissQueue: true
                        });
                        alert("名字不合法!");
                    }
                    if (member.group == "no") {
                        $.noty.consumeAlert({
                            layout: 'center',
                            type: 'error',
                            dismissQueue: true
                        });
                        alert("用户组不合法!");
                    }
                    if (member.aca == "no") {
                        $.noty.consumeAlert({
                            layout: 'center',
                            type: 'error',
                            dismissQueue: true
                        });
                        alert("学院不合法!");
                    }
                    if (member.idcard == "no") {
                        $.noty.consumeAlert({
                            layout: 'center',
                            type: 'error',
                            dismissQueue: true
                        });
                        alert("身份证不合法!");
                    }
                    if (member.birth == "no") {
                        $.noty.consumeAlert({
                            layout: 'center',
                            type: 'error',
                            dismissQueue: true
                        });
                        alert("生日不合法!");
                    }
                }
                else if (data.isok == "useridexist") {
                    $.noty.consumeAlert({
                        layout: 'center',
                        type: 'error',
                        dismissQueue: true
                    });
                    alert("用户ID已经存在");
                } else if (data.isok == "idcarderror") {
                    $.noty.consumeAlert({
                        layout: 'center',
                        type: 'error',
                        dismissQueue: true
                    });
                    alert("身份证已经存在");
                } else if (data.isok == "grouperror") {
                    $.noty.consumeAlert({
                        layout: 'center',
                        type: 'error',
                        dismissQueue: true
                    });
                    alert("用户组不存在");
                } else if (data.isok == "acaexist") {
                    $.noty.consumeAlert({
                        layout: 'center',
                        type: 'error',
                        dismissQueue: true
                    });
                    alert("学院不存在");
                } else if (data.isok == "success") {
                    var dataRow = {
                        id: data.userg.id, // 从Server端得到系统分配的id
                        user_id: user_id,
                        name: name,
                        group_id: group_name,
                        academe_id: academe_name,
                        state: statename,
                        birth: DateToUnix(birth),
                        idcard: idcard,
                        sex: sexname,
                    };

                    var srcrowid = $("#gridTable").jqGrid("getGridParam",
                        "selrow");

                    if (srcrowid) {
                        $("#gridTable").jqGrid("addRowData", data.userg.id,
                            dataRow, "before", srcrowid);
                    } else {
                        $("#gridTable").jqGrid("addRowData", data.userg.id,
                            dataRow, "first");
                    }
                    consoleDlg.dialog("close");
                    $.noty.consumeAlert({
                        layout: 'center',
                        type: 'success',
                        dismissQueue: true
                    });
                    alert("添加用户成功!");

                } else {
                    $.noty.consumeAlert({
                        layout: 'center',
                        type: 'error',
                        dismissQueue: true
                    });
                    alert("添加用户失败!");
                }
            }
        });
};
var updateUser = function () {
    var consoleDlg = $("#consoleDlg");
    var pId = $.trim(consoleDlg.find("#selectId").val());
    var academe_name = $.trim($("#academe_id").find("option:selected").text());
    var statename = $.trim($("#state").find("option:selected").text());
    var sexname = $.trim($("#sex").find("option:selected").text());
    var group_name = $.trim($("#group_id").find("option:selected").text());
    var academe_id = $.trim(consoleDlg.find("#academe_id").val());
    var group_id = $.trim(consoleDlg.find("#group_id").val());
    var state = $.trim(consoleDlg.find("#state").val());
    var sex = $.trim(consoleDlg.find("#sex").val());
    var user_id = $.trim(consoleDlg.find("#user_id").val());
    var name = $.trim(consoleDlg.find("#name").val());
    var birth = $.trim(consoleDlg.find("#birth").val());
    var idcard = $.trim(consoleDlg.find("#idcard").val());
    var params = {
        "userg.id": pId,
        "userg.user_id": user_id,
        "userg.name": name,
        "userg.group_id": group_id,
        "userg.academe_id": academe_id,
        "userg.state": state,
        "userg.birth": DateToUnix(birth),
        "userg.idcard": idcard,
        "userg.sex": sex,
    };
    if ($("#birth").val() == "") {
        $.noty.consumeAlert({
            layout: 'center',
            type: 'error',
            dismissQueue: true
        });
        alert("生日格式非法!");
    }
    var actionUrl = "acaupdateUser.action";
    $.ajax({
        url: actionUrl,
        data: params,
        dataType: "json",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        cache: false,
        error: function (textStatus, errorThrown) {
            $.noty.consumeAlert({
                layout: 'center',
                type: 'error',
                dismissQueue: true
            });
            alert("系统ajax交互错误: " + textStatus);
        },
        success: function (data, textStatus) {
            var member = eval("(" + data.result + ")");
            if (member != null) {
                if (member.userid == "no") {
                    $.noty.consumeAlert({
                        layout: 'center',
                        type: 'error',
                        dismissQueue: true
                    });
                    alert("用户ID不合法!");
                }
                if (member.name == "no") {
                    $.noty.consumeAlert({
                        layout: 'center',
                        type: 'error',
                        dismissQueue: true
                    });
                    alert("名字不合法!");
                }
                if (member.group == "no") {
                    $.noty.consumeAlert({
                        layout: 'center',
                        type: 'error',
                        dismissQueue: true
                    });
                    alert("用户组不合法!");
                }
                if (member.aca == "no") {
                    $.noty.consumeAlert({
                        layout: 'center',
                        type: 'error',
                        dismissQueue: true
                    });
                    alert("学院不合法!");
                }
                if (member.idcard == "no") {
                    $.noty.consumeAlert({
                        layout: 'center',
                        type: 'error',
                        dismissQueue: true
                    });
                    alert("身份证不合法!");
                }
            }
            else if (data.isok == "useridexist") {
                $.noty.consumeAlert({
                    layout: 'center',
                    type: 'error',
                    dismissQueue: true
                });
                alert("用户ID已经存在");
            } else if (data.isok == "idcarderror") {
                $.noty.consumeAlert({
                    layout: 'center',
                    type: 'error',
                    dismissQueue: true
                });
                alert("身份证已经存在");
            } else if (data.isok == "grouperror") {
                $.noty.consumeAlert({
                    layout: 'center',
                    type: 'error',
                    dismissQueue: true
                });
                alert("用户组不存在");
            } else if (data.isok == "acaexist") {
                $.noty.consumeAlert({
                    layout: 'center',
                    type: 'error',
                    dismissQueue: true
                });
                alert("学院不存在");
            } else if (data.isok == "success") {
                var dataRow = {
                    id: pId,
                    user_id: user_id,
                    name: name,
                    group_id: group_name,
                    academe_id: academe_name,
                    state: statename,
                    birth: DateToUnix(birth),
                    idcard: idcard,
                    sex: sexname,
                };
                $("#gridTable").jqGrid("setRowData", pId, dataRow, {
                    color: "#FF0000"
                });

                consoleDlg.dialog("close");
                $.noty.consumeAlert({
                    layout: 'center',
                    type: 'success',
                    dismissQueue: true
                });
                alert("用户更新成功!");

            } else {
                $.noty.consumeAlert({
                    layout: 'center',
                    type: 'error',
                    dismissQueue: true
                });
                alert("修改用户组失败!");
            }
        }
    });
};
var deleteUser = function () {
    var consoleDlg = $("#consoleDlgdel");
    var ids = jQuery("#gridTable").getGridParam("selarrrow");
    var params = {
        "idarray": ids.toString()
    };
    var actionUrl = "acadeleteUser.action";
    $.ajax({
        url: actionUrl,
        data: params,
        dataType: "json",
        cache: false,
        error: function (textStatus, errorThrown) {
            $.noty.consumeAlert({
                layout: 'center',
                type: 'error',
                dismissQueue: true
            });
            alert("系统ajax交互错误: " + textStatus);
        },
        success: function (data, textStatus) {
            if (data.sessionstate == "timeout") {
                $.noty.consumeAlert({
                    layout: 'center',
                    type: 'error',
                    dismissQueue: true
                });
                alert("您的session已过期，请重新登陆");
                window.open('/bjtuteach/index.action', '_top');
            }
            else if (data.isok == "success") {
                if (ids.toString().contains(",")) {
                    var arr = ids.toString().split(',');
                    $.each(arr, function (i, n) {
                        if (arr[i] != "") {
                            $("#gridTable").jqGrid("delRowData", n);
                        }
                    });
                } else {
                    $("#gridTable").jqGrid("delRowData", ids);
                }
                consoleDlg.dialog("close");
                $.noty.consumeAlert({
                    layout: 'center',
                    type: 'success',
                    dismissQueue: true
                });
                alert("用户删除成功!");

            } else {
                $.noty.consumeAlert({
                    layout: 'center',
                    type: 'error',
                    dismissQueue: true
                });
                alert("删除用户失败!");
            }
        }
    });
};
function getAcademe() {
    var str = "";
    $
        .ajax({
            type: "get",
            async: false,
            url: "acafindAcademe.action",
            success: function (data) {
                if (data != null) {
                    var length = data.aca.length;
                    for (var i = 0; i < length; i++) {
                        if (i != length - 1) {
                            str += data.aca[i].id + ":"
                                + data.aca[i].academe_name + ";";
                        } else {
                            str += data.aca[i].id + ":"
                                + data.aca[i].academe_name;
                        }
                    }
                }
            }
        });
    return str;
}
function getGroup() {
    var str = "";
    $.ajax({
        type: "get",
        async: false,
        url: "acafindGroup.action",
        success: function (data) {
            if (data != null) {
                var length = data.gr.length;
                for (var i = 0; i < length; i++) {
                    if (i != length - 1) {
                        str += data.gr[i].id + ":" + data.gr[i].group_name
                            + ";";
                    } else {
                        str += data.gr[i].id + ":" + data.gr[i].group_name;
                    }
                }
            }
        }
    });
    return str;
}
function DateToUnix(date) {
    var arr = date.toString().split('-');
    year = arr[0];
    month = arr[1] - 1;
    day = arr[2];
    var oDate =
        new Date(year, month, day);
    return (oDate.getTime() / 1000);
}
function UnixToDate(unixTime) {
    return new Date(parseInt(unixTime) * 1000).toLocaleString().replace(/年|月/g, '-').replace(/日/g, "").substring(0, 9);
}
