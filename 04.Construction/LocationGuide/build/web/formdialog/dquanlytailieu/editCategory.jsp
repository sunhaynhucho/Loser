<%-- 
    Document   : editCategory
    Created on : Feb 7, 2015, 11:41:17 AM
    Author     : SonDV
--%>

<%@page import="java.util.List"%>
<%@page import="com.elcom.omap.util.OmapUtils"%>
<%@page import="com.elcom.omap.database.DBActions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int catid = OmapUtils.parserInteger(request.getParameter("cid"));
    Object[] obj = DBActions.getListCategoryId(catid);
    List lUser = DBActions.getListCaterotyCha();

%>


<input type="hidden" id="idEditCate" value="<%=catid%>" />
<input type="hidden" id="idEditParent" value="<%=obj[2]%>" />
<div style="padding:10px 60px 20px 60px">
    <table cellpadding="5">
        <tr>
            <td>Tên danh mục:</td>
            <td><input class="easyui-textbox" type="text" name="nameCategory"
                       value="<%=obj[1]%>"
                       data-options="prompt:'Tên danh mục...',required:true"></input></td>
        </tr>
        <tr>
            <td>Miêu tả:</td>
            <td><input class="easyui-textbox" name="descCategory" 
                       value="<%=obj[3]%>"
                       data-options="prompt:'Miêu tả...',multiline:true,required:true" style="height:60px"></input></td>
        </tr>
        <tr>
            <td>Danh mục cha:</td>
            <td>
                <input class="easyui-combotree" name="selectCha" id="ccbDanhMuc"
                       data-options="url:'<%=request.getContextPath()%>/QuanLyTaiLieuServices?options=LoadTreeDanhMuc',
                       method:'get',loadFilter: function(rows){
                       return convert(rows);
                       },onLoadSuccess:function(){setVaueccbDanhMuc();}" 
                       style="width:175px;">
                
            </td>
        </tr>
    </table>
    <div style="text-align:center;padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitEditCategory()">Đồng ý</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Đóng</a>
    </div>
</div>

<script type="text/javascript" >
    function setVaueccbDanhMuc() {
    var parentId = $("#idEditParent").val();
    $("#ccbDanhMuc").combotree('setValue',parentId);
    };
</script>