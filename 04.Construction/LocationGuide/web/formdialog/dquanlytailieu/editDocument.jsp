<%-- 
    Document   : editDocument
    Created on : Feb 7, 2015, 11:28:09 AM
    Author     : elcom154
--%>

<%@page import="java.util.List"%>
<%@page import="com.elcom.omap.database.DBActions"%>
<%@page import="com.elcom.omap.util.OmapUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int did = OmapUtils.parserInteger(request.getParameter("did"));
    Object[] obj = DBActions.getListDocumentId(did);
%>
<table width="100%">
    <tr>
        <td align="center">
            <input type="hidden" id="idDocId" value="<%=obj[0]%>" />
            <input type="hidden" id="idEditParentDoc" value="<%=obj[4]%>" />
            <div style="margin-bottom:20px">
                <div>Tên tài liệu:</div>
                <input class="easyui-textbox" type="text" name="nameDocument" id="nameDocument"
                       value="<%=OmapUtils.parserObjectToString(obj[1])%>"
                       data-options="prompt:'Tên tài liệu...',required:true"></input>
            </div>
            <div style="margin-bottom:20px">
                <div>Từ khóa:</div>
                <input class="easyui-textbox" id="searchKey" name="searchKey" value="<%=OmapUtils.parserObjectToString(obj[2])%>"
                       data-options="prompt:'Từ khóa tìm kiếm...'" style="width:200px">
            </div>
            <div style="margin-bottom:20px">
                <div>Chọn danh mục:</div>
                <input class="easyui-combotree" name="ccbDanhMucDoc" id="ccbDanhMucDoc"
                       data-options="prompt:'Chọn danh mục...',url:'<%=request.getContextPath()%>/QuanLyTaiLieuServices?options=LoadTreeDanhMuc',
                       method:'get',loadFilter: function(rows){
                       return convert(rows);
                       },onLoadSuccess:function(){setVaueccbDanhMuc();}" 
                       style="width:175px;">
            </div>

            <div style="text-align:center;padding:5px">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitEditDoc()" style="width: 45px">Sửa</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearFormEditDoc()">Đóng</a>
            </div>
        </td>
    </tr>
</table>


<script type="text/javascript" >
                    function setVaueccbDanhMuc() {
                        var parentId = $("#idEditParentDoc").val();
                        $("#ccbDanhMucDoc").combotree('setValue', parentId);
                    }
                    ;
</script>