<%-- 
    Document   : viewShowEditResult
    Created on : Mar 25, 2015, 11:42:17 AM
    Author     : SonDV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String data = request.getParameter("data");
    int id = Integer.parseInt(request.getParameter("id"));
%>

<script type="text/javascript">
//    $(document).ready(function() {
//        $('#wcontentMaxlength').textbox();
//        $('#wcontentMaxlength').textbox('textbox').attr('maxlength', $('#wcontentMaxlength').attr("maxlength"));
//        $('#wcontentMaxlength').textbox('textbox').keyup(function() {
//            displayWordCounter();
//        });
//    });

    function displayWordCounterEditRS() {

        var getTextValue = document.frmKFC.wcontentEditRS.value;
        var getTextLength = getTextValue.length;

        document.getElementById('testTextarea212').innerHTML = getTextLength;
        document.getElementById('testTextarea312').innerHTML = 1000 - getTextLength;

    }

    $(function() {
        var getTextValue = document.frmKFC.wcontentEditRS.value;
        var getTextLength = getTextValue.length;

        document.getElementById('testTextarea212').innerHTML = getTextLength;
        document.getElementById('testTextarea312').innerHTML = 1000 - getTextLength;
    });
</script>

<form id="frmEditRow" name="frmKFC" method="post" novalidate>
    <input type="hidden" id="idEditResult" value="<%=id%>">
    <div class="fitem">
        <table>
            <tr>
                <td>
                    <textarea id="wcontentEditRS" name="wcontentEditRS"  onkeyup="displayWordCounterEditRS();" maxlength="1000" style="height:290px;width: 520px;border: #33ccff solid 1px"><%=data%></textarea>
                </td>
            </tr>
        </table>
    </div>
    <div class="fitem">
        <span >Số ký tự hiện tại :<strong> <span id="testTextarea212">0</span></strong></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <span>Số ký tự còn lại:<strong> <span id="testTextarea312">1000</span></strong></span>
    </div>
    <br>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" id="saveEditData" iconCls="icon-ok" onclick="saveEditDataResult()" style="width:90px">Sửa</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dgABCD').dialog('close')" style="width:90px">Đóng</a>
    </div>
</form>