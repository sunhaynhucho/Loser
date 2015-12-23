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
<input type="hidden" id="txtWorkId" value="<%=wid%>" />
<table style="width: 100%;">
    <tr>
        <td>

            <table class="easyui-datagrid" style="width:350px;height:250px" id="griDoc"
                   data-options="singleSelect:true,onBeforeLoad:function(){loadDuLieuDocument();}">

                <thead>
                    <tr>
                        <th data-options="field:'dname',width:'135px',align:'center'">Tên tài liệu</th>
                        <th data-options="field:'dtime',width:'75px',align:'center'">Thời gian</th>
                        <th data-options="field:'ddownload',align:'center'">Tải về</th>
                        <th data-options="field:'dview',align:'center'">Xem</th>
                        <th data-options="field:'dremove',align:'center'">Xóa</th>
                    </tr>
                </thead>
            </table>
        </td>
        <td style="width: 10px;"></td>
        <td style="vertical-align: top;text-align: left;">

            <div style="margin-bottom:20px">
                <div>Tên tài liệu:</div>
                <input class="easyui-textbox" id="txtDocName" name="txtDocName"  style="width:100%">
            </div>
            <form id="upLoadFileId" action="<%=request.getContextPath()%>/OmapUploadFileServices" method="post" 
                  enctype="multipart/form-data" acceptcharset="UTF-8">
                <div style="margin-bottom:20px">
                    <input class="easyui-filebox" id="fileupload" name="file[]" data-options="prompt:'Choose a file...'" style="width:100%">
                </div>
                <div>
                    <a href="#" class="easyui-linkbutton" id="submitUploadFile" onclick="submitUploadFile()" style="width:100%">Upload</a>
                </div>
            </form>
        </td>
    </tr>
</table>

<script type="text/javascript" >
                        function submitUploadFile() {
                            var workId = $("#txtWorkId").val();
                            var txtDocName = $("#txtDocName").textbox('getValue');
                            if (txtDocName == null || txtDocName == "") {
                                $.messager.alert("Thông báo", "Bạn chưa điền tiêu đề cho tài liệu", "info");
                                return;
                            }
                            var tl = $("#fileupload").filebox('getValue');
                            if (tl == null || tl == "") {
                                $.messager.alert("Thông báo", "Bạn chưa chọn file upload", "info");
                                return;
                            }

                            $('#submitUploadFile').linkbutton('disable');

                            $.messager.progress({
                                title: 'Xin đợi ',
                                msg: 'Đang xử lý dữ liệu...',
                                interval: 1000
                            });
                            $('#upLoadFileId').form('submit', {
                                url: requestUrl + '/OmapUploadFileServices',
                                accept: "text/plain; charset=utf-8",
                                onSubmit: function(param) {
                                    param.options = 'UpLoadDocument';
                                    param.txtWorkId2 = workId;
                                    param.txtDocName = txtDocName;
                                }, success: function(data) {
                                    $.messager.progress('close');
                                    var data = eval('(' + data + ')');
                                    if (data.code == 0) {
                                        //$( "#infoBuoc3" ).html("Contact hợp lệ:" + data.gcount + ", số contact không hợp lệ:" + data.gcountnok);
                                        $.messager.alert("Thông báo", "Thành công", "info");
                                        loadDuLieuDocument();
                                        $('#dg').datagrid('reload');
                                        $('#upLoadFileId').form('clear');
                                        $("#txtDocName").textbox('setValue', '');
                                        $('#submitUploadFile').linkbutton('enable');
                                    } else {
                                        $.messager.alert("Thông báo", data.detail, "info");
                                        $('#submitUploadFile').linkbutton('enable');
                                    }

                                }
                            });
                        }
</script>
