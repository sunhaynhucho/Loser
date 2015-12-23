<%-- 
    Document   : viewdocumnet
    Created on : Feb 5, 2015, 10:23:54 AM
    Author     : elcom154
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<%
    String wid = request.getParameter("wid");
%>
<script type="text/javascript">
//    $(document).ready(function() {
//        $('#txtComment').textbox();
//        $('#txtComment').textbox('textbox').attr('maxlength', $('#txtComment').attr("maxlength"));
//    });
//    
    function displayWordCounterAddCMRS() {

        var getTextValue = document.upLoadFileIdRS.txtContent.value;
        var getTextLength = getTextValue.length;

        document.getElementById('testTextarea2222').innerHTML = getTextLength;
        document.getElementById('testTextarea3333').innerHTML = 1000 - getTextLength;

    }

</script>
<input type="hidden" id="txtWorkIdRS" value="<%=wid%>" />
<table style="width: 100%;">
    <tr>
        <td>

            <table class="easyui-datagrid" style="width:480px;height:345px" id="griDoc"
                   data-options="nowrap:false,singleSelect:true,onBeforeLoad:function(){loadDuLieuResult();}">

                <thead>
                    <tr>
                        <th data-options="field:'dcontent',width:'145px',align:'center'">Nội dung</th>
                        <th data-options="field:'dname',width:'85px',align:'center'">Tên File</th>
                        <th data-options="field:'dtime',width:'75px',align:'center'">Thời gian</th>
                        <th data-options="field:'ddownload',align:'center'">Tải về</th>
                        <th data-options="field:'dview',align:'center'">Xem</th>
                        <th data-options="field:'dedit',align:'center'">Sửa</th>
                        <th data-options="field:'dremove',align:'center'">Xóa</th>
                    </tr>
                </thead>
            </table>
        </td>
        <td style="width: 10px;"></td>
        <td style="vertical-align: top;text-align: left;">

            <div style="margin-bottom:20px">
                <div>Tên File:</div>
                <input class="easyui-textbox" id="txtDocNameRS" name="txtDocNameRS"  style="width:100%">
            </div>
            <form id="upLoadFileIdRS" name="upLoadFileIdRS" action="<%=request.getContextPath()%>/OmapUploadFileServices" method="post" 
                  enctype="multipart/form-data" acceptcharset="UTF-8">
                <div style="margin-bottom:20px">
                    <input class="easyui-filebox" id="fileuploadRS" name="file[]" data-options="prompt:'Choose a file...'" style="width:100%">
                </div>
                <div>
                    <textarea id="txtContent" name="txtContent" placeholder="Nhập nội dung kết quả ..."  onkeyup="displayWordCounterAddCMRS();" maxlength="1000" style="height:200px; width: 350px;border: #33ccff solid 1px"></textarea>
                    <span >Số ký tự hiện tại :<strong> <span id="testTextarea2222">0</span></strong></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span>Số ký tự còn lại:<strong> <span id="testTextarea3333">1000</span></strong></span>
                </div>
                <br>
                <div>
                    <a href="#" class="easyui-linkbutton" id="submitUploadFileResult" onclick="submitUploadFileResult()" style="width:100%">Upload</a>
                </div>
            </form>
        </td>
    </tr>
</table>

<script type="text/javascript" >
    function submitUploadFileResult() {

        var workId = $("#txtWorkIdRS").val();
        var txtDocName = $("#txtDocNameRS").textbox('getValue');
        var txtContent = $("#txtContent").val();
        var tl = $("#fileuploadRS").filebox('getValue').toString();

        if (txtContent.trim() == "") {
            if (txtDocName.trim() == "") {
                $.messager.alert("Thông báo", "Bạn chưa điền tiêu đề cho file", "info");
                return;
            }
            else if (tl.length == 0) {
                $.messager.alert("Thông báo", "Bạn chưa chọn file upload", "info");
                return;
            }
        } else {
            if (tl.length > 0) {
                if (txtDocName.trim() == "") {
                    $.messager.alert("Thông báo", "Bạn chưa điền tiêu đề cho file", "info");
                    return;
                }
            }
            else if (txtDocName.trim() != "") {
                if (tl.length == 0) {
                    $.messager.alert("Thông báo", "Bạn chưa chọn file upload", "info");
                    return;
                }
            }
        }

        $('#submitUploadFileResult').linkbutton('disable');

        $.messager.progress({
            title: 'Xin đợi ',
            msg: 'Đang xử lý dữ liệu...',
            interval: 1000
        });
        $('#upLoadFileIdRS').form('submit', {
            url: requestUrl + '/OmapUploadFileResultServices',
            accept: "text/plain; charset=utf-8",
            onSubmit: function(param) {
                param.options = 'UpLoadResult';
                param.txtWorkId2 = workId;
                param.txtDocName = txtDocName;
                param.txtContent = txtContent;
            }, success: function(data) {
                $.messager.progress('close');
                var data = eval('(' + data + ')');
                if (data.code == 0) {
                    //$( "#infoBuoc3" ).html("Contact hợp lệ:" + data.gcount + ", số contact không hợp lệ:" + data.gcountnok);
                    $.messager.alert("Thông báo", "Thành công", "info");
                    loadDuLieuResult();
                    $('#dg').datagrid('reload');
                    $('#upLoadFileIdRS').form('clear');
                    $("#txtDocNameRS").textbox('setValue', '');
                    $('#submitUploadFileResult').linkbutton('enable');
                } else {
                    $.messager.alert("Thông báo", data.detail, "info");
                    $('#submitUploadFileResult').linkbutton('enable');
                }

            }
        });
    }
</script>
