<%-- 
    Document   : addcomment
    Created on : Feb 5, 2015, 10:22:58 AM
    Author     : elcom154
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String wid = request.getParameter("wid");
%>
<script type="text/javascript">
//    $(document).ready(function() {
//        $('#txtComment').textbox();
//        $('#txtComment').textbox('textbox').attr('maxlength', $('#txtComment').attr("maxlength"));
//    });
//    
    function displayWordCounterAddCM() {

        var getTextValue = document.frm222.txtComment.value;
        var getTextLength = getTextValue.length;

        document.getElementById('testTextarea222').innerHTML = getTextLength;
        document.getElementById('testTextarea333').innerHTML = 1000 - getTextLength;

    }

</script>
<input type="hidden" id="txtWorkId" value="<%=wid%>"/>
<div  style="padding:10px 10px 10px 10px;">
    <form id="ff" name="frm222" method="post">
        <table cellpadding="5">
            <tr>
                <td>
                    <!--                    <input class="easyui-textbox" name="message" id="txtComment"
                                               data-options="multiline:true,prompt:'Nhập nội dung góp ý ...'" maxlength="1000" style="width: 700px;height:280px"></input>-->
                    <textarea id="txtComment" name="txtComment" placeholder="Nhập nội dung góp ý ..."  onkeyup="displayWordCounterAddCM();" maxlength="1000" style="height:260px; width: 695px;border: #33ccff solid 1px"></textarea>
                </td>
            </tr>

        </table>
    </form>
    <div id="dlg-buttons" >
        &nbsp;
        <a href="javascript:void(0)" class="easyui-linkbutton" id="submitAddComment" iconCls="icon-ok" onclick="submitAddComment()" style="width:90px">Ghi lại</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeDialogABC()" style="width:90px">Đóng</a>
        <span >Số ký tự hiện tại :<strong> <span id="testTextarea222">0</span></strong></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <span>Số ký tự còn lại:<strong> <span id="testTextarea333">1000</span></strong></span>
    </div>
</div>