/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function luuLaiHoaDon() {
    var data = $('#dg').datagrid('getRows');
    alert(data.length);
    var count = data.length;
    var strdata = "";
    for (i = 0; i < count; i++) {
        if (i != 0) {
            strdata += ";";
        }
        strdata += data[i]['pid'] + "|" + data[i]['pgia'] + "|" + data[i]['psoluong'];
    }
    alert(strdata);
    var soTienNo = $('#soTienNo').val();
    var tongTien = $('#txtGiaTien').val();
    var tenKH = $('#txtTenKH').val();
    var diaChi = $('#txtDiaChi').val();
    $.ajax({
        url: requestUrl + "/BanHangServices",
        type: "POST",
        cache: false,
        data: {
            options: "TaoHoaDon",
            listsanpham: strdata,
            tenkh: '',
            diachikh: '',
            tongtien: tongTien,
            notien: soTienNo
        },
        dataType: "json",
        success: function(data) {
            if (data.code == "0") {
                $.messager.alert('Thông báo', data.detail);
            } else {
                $.messager.alert('Thông báo', data.detail, 'error');
            }
        },
        error: ajaxFail
    });
}
