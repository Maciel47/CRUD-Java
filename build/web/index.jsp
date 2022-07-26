<%-- 
    Document   : index
    Created on : 11/03/2022, 15:18:26
    Author     : Tassia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <div class="row">
            <div class="col-sm-3 bg-primary text-white"><jsp:include page="menu.jsp" /></div>            
        </div>
            ${mensagem} ${oAdministradorLogado.nome} ${oFornecedorLogado.nome} ${oClienteLogado.nome}
        <br>
</html>
