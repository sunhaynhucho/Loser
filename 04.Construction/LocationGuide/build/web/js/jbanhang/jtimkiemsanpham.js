$(function(){
    $('#cg').combogrid({
        panelWidth:500,
        url: requestUrl +'/BanHangServices?options=TimKiemSPDeBan',
        delay:1000,
        idField:'masp',
        textField:'tensp',
        mode:'remote',
        fitColumns:true,
        columns:[[
        {
            field:'tensp',
            title:'Tên sản phẩm'
        },

        {
            field:'giaban1sp',
            title:'Giá bán 1'
        },

        {
            field:'giaban2sp',
            title:'Giá bán 2',
            align:'right'
        },

        {
            field:'giaban3sp',
            title:'Giá bán 3',
            align:'right'
        },

        {
            field:'giaban4sp',
            title:'Giá bán 4'
        },
        {
            field:'soluongkho',
            title:'Số lượng trong kho'
        },
        {
            field:'khuyenmai',
            title:'Khuyến mãi'
        }
        ]]
    });
});

$(function() {
    $('#dgList').datagrid({
        loadFilter:pagerFilter
    }).datagrid('reload',{
        options:'GetListTimKiemSanPham'
    });
    var pager = $('#dgList').datagrid('getPager');
    pager.pagination({
        onSelectPage:function(pageNum, pageS){
            $('#dgList').datagrid('reload',{
                options:'GetListTimKiemSanPham',
                tensp:'',
                masp:'',
                pagenumber: pageNum,
                pagesize: pageS
            });
        }
        
    });
})


function submitTimKiem(){
    var g = $('#cg').combogrid('grid');	// get datagrid object
    var tenSP = $('#cg').val();//g.datagrid('getSelected');	// get the selected row
    var maSP = $( "#txtMaSP" ).val();
    
    $('#dgList').datagrid('load',{
        options:'GetListTimKiemSanPham',
        tensp:tenSP,
        masp:maSP
    });
    /*var pager = $('#dgList').datagrid('getPager');
    pager.pagination({
        onSelectPage:function(pageNum, pageS){
            $('#dgList').datagrid('reload',{
                options:'GetListTimKiemSanPham',
                tensp:tenSP.tensp,
                masp:maSP,
                pagenumber: pageNum,
                pagesize: pageS
            });
        }
        
    });*/
}
