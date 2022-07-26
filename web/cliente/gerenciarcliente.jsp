<%-- 
    Document   : gerenciarCliente
    Created on : 25 de mar de 2022, 11:16:19
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
            <div class="text-white col-3"><jsp:include page="../menu.jsp"/></div>
            <div class="col-sm-9">
                <div class="col-sm-10">

                    <form action="SalvarCliente" class="col-9 container">
                        <input type="hidden" value="${oCliente.idPessoa}" name="idpessoa">

                        <h1>Cadastrar Cliente</h1>

                        <label for="nome" class="form-label">Nome</label>
                        <input type="text" name="nome" class="form-control" value="${oCliente.nome}">
                        <br />

                        <div class="row">
                            <div class="form-group col-6">
                                <label for = "CPF" class="form-label">CPF</label>
                                <input type="text" name="cpf" class="form-control" value="${oCliente.cpf}">
                            </div>
                            <div class="form-group col-6">
                                <label for="datanascimento" class="form-label">Data de Nascimento</label>
                                <input type="date" name="datanascimento" class="form-control" value="${oCliente.datanascimento}">
                            </div>
                            <div class="form-group col-6">
                                <label for="login" class="form-label">Login</label>
                                <input type="text" name="login" class="form-control" value="${oCliente.login}">
                            </div>
                            <div class="form-group col-6">
                                <label for="senha" class="form-label">Senha</label>
                                <input type="password" name="senha" class="form-control" value="${oCliente.senha}">
                            </div>
                        </div>
                        <br />

                        <label for="cidade" class="form-label">Cidade</label>
                        <select name="idcidade">
                            <br />
                            <option value="">Selecione</option>
                            <c:forEach var="cidade" items="${listarCidade}">
                                <option value="${cidade.idCidade}" 
                                        ${oCliente.cidade.idCidade == cidade.idCidade ? "selected": ""}>
                                    ${cidade.idCidade} - ${cidade.estado.siglaEstado} - ${cidade.nomeCidade}
                                </option>
                            </c:forEach>    
                        </select>
                        <br />

                        <label for="observacao" class="form-label">Observação</label>
                        <textarea name="observacao" class="form-control" value="${oCliente.observacao}"></textarea>
                        <br />
                        <label for="permitelogin" class="form-label">Permite Login: </label>
                        <select name="permitelogin">
                            <option value="N" ${oCliente.permiteLogin == 'N' ? "selected" : ""}>Não</option>
                            <option value="S" ${oCliente.permiteLogin == 'S' ? "selected" : ""}>Sim</option>
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
                </div>
                <!--listar Clientes-->
                <h1>Lista de Clientes</h1>

                <table  class="table table-success table-striped">

                    <thead>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>CPF</th>
                    <th>Observação</th>
                    <th>Estado</th>
                    <th>Cidade</th>
                    <th colspan="2">Editar</th>
                    </thead>
                    <tbody>                
                        <c:forEach var="cliente" items="${listarCliente}">
                            <tr class="table-light">
                                <td>${cliente.idCliente}</td>
                                <td>${cliente.nome}</td>
                                <td>${cliente.cpf}</td>
                                <td>${cliente.observacao}</td>
                                <td>${cliente.cidade.estado.siglaEstado}</td>
                                <td>${cliente.cidade.nomeCidade}</td>
                                <td>
                                    <a class="btn btn-danger" href="ExcluirCliente?excluirCliente=${cliente.idPessoa}">Excluir</a>
                                </td>
                                <td>
                                    <a class="btn btn-primary" href="CarregarCliente?carregarCliente=${cliente.idPessoa}">Editar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script src="../assets/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
</body>
</html>
