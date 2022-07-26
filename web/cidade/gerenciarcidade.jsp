<%-- 
    Document   : gerenciarcidade
    Created on : 23/11/2021, 17:53:48
    Author     : Tassia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <meta charset="UTF-8">
        <title>JSP Page</title>

    </head>
    <body>
        <div class="row">
            <div class="col-sm-3 bg-dark text-white"><jsp:include page="../menu.jsp" /></div>
            <div class="col-sm-9">
                <div class="row">                       
                    <div class="col-sm-6">
                        
                        <h1>Gerenciar Cidade</h1>

                        <form action="SalvarCidade" method="post">
                            <label for="idcidade" class="form-label">Id Cidade</label>
                            <input type="number" name="idcidade" class="form-control" value="${oCidade.idCidade}" readonly >
                            <br /><br />
                            <label class="form-label">Nome Cidade</label>
                            <input type="text" class="form-control" name="nomecidade" value="${oCidade.nomeCidade}">
                            <br /><br />
                            <label class="form-label">Estado</label>
                            <select name="idestado" class="form-control">
                                <c:forEach var="estado" items="${estados}">
                                    <option value="${estado.idEstado}">${estado.siglaEstado}</option>
                                </c:forEach>
                            </select>
                            <br /><br />
                            <input  class="btn btn-primary" type="submit" value="Salvar">
                        </form>
                        <h3>${mensagem}</h3>
                    </div>
                </div>
                <h1>Lista de Cidades</h1>

                <table  class="table table-success table-striped">

                    <thead>
                    <th>ID Cidade</th>
                    <th>Nome Cidade</th>
                    <th>Sigla Estado</th>
                    <th>Nome Estado</th>
                    <th colspan="2">Editar</th>
                    </thead>
                    <tbody>                
                        <c:forEach var="cidade" items="${listadecidades}">
                            <tr class="table-light">
                                <td>${cidade.idCidade}</td>
                                <td>${cidade.nomeCidade}</td>
                                <td>${cidade.estado.siglaEstado}</td>
                                <td>${cidade.estado.nomeEstado}</td>
                                <td><a class="btn btn-danger" href="ExcluirCidade?excluirCidade=${cidade.idCidade}">Excluir</a></td>
                                <td><a class="btn btn-primary" href="CarregarCidade?carregarCidade=${cidade.idCidade}">Editar</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
