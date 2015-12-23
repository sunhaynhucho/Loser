<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/pdfobject.js"></script> 
<style type="text/css">
    <!--

    #pdf {
        width: 99%;
        height: 490px;
    }

    #pdf p {
        padding: 1em;
    }

    #pdf object {
        display: block;
        border: solid 1px #666;
    }

    -->
</style>
<script type="text/javascript">
    $(document).ready(function() {
        var urlFile = $("#txtUrlFileShow").val();
        var dinhdang = urlFile.substr(-3);
        if (dinhdang == "jpg" || dinhdang == "png") {
            document.getElementById('imgSrcW').src = urlFile;
        } else {
            var success = new PDFObject({url: urlFile}).embed("pdf");
            if (!success) {
                $.messager.alert('Thông báo', 'Lỗi không đọc được file');
            }
        }

    });
</script> 
<img id="imgSrcW" src=""/>
<div id="pdf"></div>