<%-- 
    Document   : addDocument
    Created on : Feb 7, 2015, 11:27:57 AM
    Author     : elcom154
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table width="100%">
    <tr>
        <td align="center">

            <form id="upLoadFileId" action="<%=request.getContextPath()%>/OmapUploadFileServices" method="post" 
                  enctype="multipart/form-data" acceptcharset="UTF-8">
                <div style="margin-bottom:20px">
                    <div>Tên tài liệu:</div>
                    <input class="easyui-textbox" id="txtDocName" name="txtDocName" data-options="prompt:'Tên tài liệu...'" style="width:200px">
                </div>
                <div style="margin-bottom:20px">
                    <div>Từ khóa:</div>
                    <input class="easyui-textbox" id="txtSearchKey" name="txtSearchKey" data-options="prompt:'Từ khóa tìm kiếm...'" style="width:200px">
                </div>
                <div style="margin-bottom:20px">
                    <div>Chọn danh mục:</div>
                    <input class="easyui-combotree" id="ccbAddDanhMuc"
                           data-options="prompt:'Chọn danh mục...',url:'<%=request.getContextPath()%>/QuanLyTaiLieuServices?options=LoadTreeDanhMuc',
                           method:'get',loadFilter: function(rows){
                           return convert(rows);
                           }" 
                           style="width:200px;">
                </div>
                <div style="margin-bottom:20px">
                    <input class="easyui-filebox" id="fileupload" name="file[]" data-options="prompt:'Chọn file...'" style="width:200px;">
                </div>
                <div>
                    <a href="#" class="easyui-linkbutton" onclick="submitAddDoc()" style="width:100px;">Upload</a>
                </div>
            </form>
        </td>
    </tr>
</table>
