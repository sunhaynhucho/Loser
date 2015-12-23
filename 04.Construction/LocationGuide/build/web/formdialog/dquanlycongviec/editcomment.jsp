<%-- 
    Document   : editcomment
    Created on : Feb 5, 2015, 10:23:07 AM
    Author     : elcom154
--%>

<%@page import="com.elcom.omap.database.DBActions"%>
<%@page import="com.elcom.omap.util.OmapUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int cid = OmapUtils.parserInteger(request.getParameter("cid"));
    String wid = request.getParameter("wid");
    String comment = DBActions.getComment(cid);
%>
<script type="text/javascript">
    
    $(function() {
        var getTextLength = $('#txtComment').val();

        document.getElementById('testTextarea2222').innerHTML = getTextLength.length;
        document.getElementById('testTextarea3333').innerHTML = 1000 - getTextLength.length;
    });
    
//    $(document).ready(function() {
//        $('#txtComment').textbox();
//        $('#txtComment').textbox('textbox').attr('maxlength', $('#txtComment').attr("maxlength"));
//    });
    
    function displayWordCounterEditCM() {

        var getTextValue = document.frm22222.txtComment.value;
        var getTextLength = getTextValue.length;

        document.getElementById('testTextarea2222').innerHTML = getTextLength;
        document.getElementById('testTextarea3333').innerHTML = 1000 - getTextLength;

    }

</script>

<input type="hidden" id="txtCmnId" value="<%=cid%>" />
<input type="hidden" id="txtWorkId" value="<%=wid%>" />
<div style="padding:10px 10px 10px 10px">
    <form id="ff" name="frm22222" method="post">
        <table cellpadding="5">
            <tr>
                <td>
<!--                    <input class="easyui-textbox" name="message" required="true" id="txtComment"
                           data-options="multiline:true" maxlength="1000" value="" style="height:60px"></input>-->
<textarea id="txtComment" name="txtComment" placeholder="Nhập nội dung góp ý ..."  onkeyup="displayWordCounterEditCM();" maxlength="1000" style="height:260px; width: 695px;border: #33ccff solid 1px" ><%=comment%></textarea>
                </td>
            </tr>
        </table>
    </form>
    <div style="text-align:left;padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" id="submitEditComment" iconCls="icon-ok" onclick="submitEditComment()">Ghi lại</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeDialogABC()">Đóng</a>
        <span >Số ký tự hiện tại :<strong> <span id="testTextarea2222">0</span></strong></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <span>Số ký tự còn lại:<strong> <span id="testTextarea3333">1000</span></strong></span>
    </div>
</div>