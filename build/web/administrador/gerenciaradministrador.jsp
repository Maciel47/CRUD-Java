<%-- 
    Document   : gerenciarAdministrador
    Created on : 26 de abr de 2022, 20:59:15
    Author     : Ghost
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="row">
            <div class="text-white col-3">
                <jsp:include page="../menu.jsp"/>
            </div>
            <div class="col-9">
                <form action="SalvarAdministrador" method="POST" enctype="multipart/form-data" class="col-9 container">
                    <input type="hidden" value="${oAdministrador.idPessoa}" name="idpessoa">
                    <h1>Cadastrar Administrador</h1>
                    <label for="nome" class="form-label">Nome</label>
                    <input type="text" name="nome" class="form-control" value="${oAdministrador.nome}">
                    <br />
                    <div class="row">
                        <div class="form-group col-6">
                            <label for = "CPF" class="form-label">CPF</label>
                            <input type="text" name="cpf" class="form-control"  value="${oAdministrador.cpf}">
                        </div>
                        <div class="form-group col-6">
                            <label for="datanascimento" class="form-label">Data de Nascimento</label>
                            <input type="date" name="datanascimento" class="form-control" value="${oAdministrador.datanascimento}">
                        </div>
                        <div class="form-group col-6">
                            <label for="login" class="form-label">Login</label>
                            <input type="text" name="login" class="form-control" value="${oAdministrador.login}">
                        </div>
                        <div class="form-group col-6">
                            <label for="senha" class="form-label">Senha</label>
                            <input type="password" name="senha" class="form-control" value="${oAdministrador.senha}">
                        </div>
                        <div class="col-10">
                            <label for="caminhofoto" class="form-label">Caminho da foto</label>
                            <input name="caminhofoto" type="file" class="form-control" value="${oAdministrador.caminhofoto}">
                            <br/>
                        </div>
                        <div class="col-10">
                            <label for="situacao" class="form-label">Situação</label>
                            <select name="situacao">
                                <option selected="selected">Escolha</option>
                                <option value="1" ${oAdministrador.situacao == '1' ? "selected" : ""}>Ativo</option>
                                <option value="0" ${oAdministrador.situacao == '0' ? "selected" : ""}>Desativado</option>
                            </select>
                        </div>                        
                    </div>
                    <br />
                    <label for="cidade" class="form-label">Cidade</label>
                    <select name="idcidade">
                        <br />
                        <option value="">Selecione</option>
                        <c:forEach var="cidade" items="${listadecidades}">
                            <option value="${cidade.idCidade}" 
                                    ${oAdministrador.cidade.idCidade == cidade.idCidade ? "selected": ""}>
                                ${cidade.idCidade} - ${cidade.nomeCidade} - ${cidade.estado.siglaEstado}
                            </option>
                        </c:forEach>    
                    </select>
                    <br />
                    <label for="observacao" class="form-label">Observação</label>
                    <textarea name="observacao" class="form-control"></textarea>
                    <br />
                    <label for="permitelogin" class="form-label">Permite Login: </label>
                    <select name="permitelogin">
                        <option value="N" ${oAdministrador.permiteLogin == 'N' ? "selected" : ""}>Não</option>
                        <option value="S" ${oAdministrador.permiteLogin == 'S' ? "selected" : ""}>Sim</option>
                    </select>
                    <div class="container col-6">
                        <input type="submit" value="Salvar" class="form-control bg-primary text-white">
                    </div>
                    <div class="container col-6">
                        <input type="reset" value="Limpar" class="form-control bg-secondary text-white">
                    </div>
                </form>
                ${mensagem}
                <br />

                <h1>Lista de Administradores</h1>

                <table  class="table table-success table-striped">

                    <thead>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>CPF</th>
                    <th>Sigla Estado</th>
                    <th>Nome Cidade</th>
                    <th>Caminho da foto</th>
                    <th colspan="2">Editar</th>
                    </thead>
                    <tbody>                
                        <c:forEach var="administrador" items="${listaradministrador}">
                            <tr class="table-light">
                                <td>${administrador.idPessoa}</td>
                                <td>${administrador.nome}</td>
                                <td>${administrador.cpf}</td>
                                <td>${administrador.cidade.estado.siglaEstado}</td>
                                <td>${administrador.cidade.nomeCidade}</td>
                                <td>${administrador.caminhofoto}</td>
                                <td><img width="50px" height="50px" src="${pageContext.request.contextPath}/fotos/${administrador.caminhofoto}"></td>
                                <td><a class="btn btn-danger" href="ExcluirAdministrador?excluirAdministrador=${administrador.idPessoa}">Excluir</a></td>
                                <td><a class="btn btn-primary" href="CarregarAdministrador?carregarAdministrador=${administrador.idPessoa}">Editar</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div> 

        <script src="../assets/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
    </body>
</html>
