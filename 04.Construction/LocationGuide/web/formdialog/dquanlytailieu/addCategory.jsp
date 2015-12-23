<%-- 
    Document   : addCategory
    Created on : Feb 7, 2015, 11:41:09 AM
    Author     : SonDV
--%>
<%@page import="com.elcom.omap.database.DBActions"%>
<%@page import="java.util.List"%>
<%@page import="com.elcom.omap.common.OmapSessionUtils"%>
<%@page import="com.elcom.omap.util.OmapUtils"%>
<%@page import="com.elcom.omap.util.OmapUtils"%>
<%
    int userId = OmapUtils.parserInteger(OmapSessionUtils.getUserId(request));
    List lUser = DBActions.getListCaterotyCha();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div style="padding:10px 60px 20px 60px">
    <form id="ff" method="post">
        <table cellpadding="5">
            <tr>
                <td>Tên danh mục:</td>
                <td><input class="easyui-textbox" type="text" name="nameCategory" data-options="prompt:'Tên danh mục...',required:true"></input></td>
            </tr>
            <tr>
                <td>Miêu tả:</td>
                <td><input class="easyui-textbox" name="descCategory" data-options="prompt:'Miêu tả...',multiline:true,required:true" style="height:60px"></input></td>
            </tr>
            <tr>
                <td>Danh mục cha:</td>
                <td>
                    <input class="easyui-combotree" name="selectCha" id="ccbAddselectCha"
                       data-options="prompt:'Chọn danh mục...',url:'<%=request.getContextPath()%>/QuanLyTaiLieuServices?options=LoadTreeDanhMuc',
                       method:'get',loadFilter: function(rows){
                       return convert(rows);
                       }" 
                       style="width:175px;">
                    
                </td>
            </tr>
        </table>
    </form>
    <div style="text-align:center;padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitAddCategory()">Đồng ý</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Đóng</a>
    </div>
</div>