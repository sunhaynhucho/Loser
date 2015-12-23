<%-- 
    Document   : xuatbaocao
    Created on : Jun 2, 2015, 4:08:39 PM
    Author     : SonDV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="../js/datagrid-detailview.js"></script>
<script type="text/javascript" src="../js/jquanlycongviec/jhome.js"></script>
<!DOCTYPE html>
<script type="text/javascript" >
    var requestUrl = "<%=request.getContextPath()%>";
</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Xuất báo cáo</title>
    </head>
    <body>
        <br><br>
        <div id="tbBaocao" style="padding:2px 5px;">
            &nbsp;&nbsp;&nbsp;    Từ ngày: <input class="easyui-datebox" id="startDateBaocao" data-options="formatter:myformatter,parser:myparser" style="width:110px">
            &nbsp;&nbsp;&nbsp;    Đến ngày: <input class="easyui-datebox" id="endDateBaocao" data-options="formatter:myformatter,parser:myparser" style="width:110px">
            <br><br>&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; <a href="#" class="easyui-linkbutton" onclick="xuatBaocao(1);" iconCls="icon-ok">Xuất báo cáo người nhận việc</a>
            <a href="#" class="easyui-linkbutton" onclick="xuatBaocao(0);" iconCls="icon-ok">Xuất báo cáo việc của mình</a>
        </div>
    </body>
</html>
<script type="text/javascript" >
    function xuatBaocao(key) {
        var startDate = $("#startDateBaocao").datebox("getValue");
        var endDate = $("#endDateBaocao").datebox("getValue");

        if (startDate == null || startDate == "") {
            $.messager.alert('Thông báo', 'Chưa chọn ngày bắt đầu', 'info');
            return;
        }
        if (endDate == null || endDate == "") {
            $.messager.alert('Thông báo', 'Chưa chọn ngày kết thúc', 'info');
            return;
        }
        if (myparser(startDate) > myparser(endDate)) {
            $.messager.alert('Thông báo', 'Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc', 'info');
            return;
        }

        $.ajax({
            type: "POST",
            url: requestUrl + "/QuanTriHeThongServices",
            data: {
                options: "XuatBaoCao",
                key: key,
                startDate: startDate,
                endDate: endDate
            },
            dataType: "json",
            success: function(data) {
                if (data.code == 0) {
                    window.location = data.url;
                } else {
                    $.messager.alert('Thông báo', data.detail, 'error');
                }
            },
            error: ajaxFail
        });
    }

</script>
