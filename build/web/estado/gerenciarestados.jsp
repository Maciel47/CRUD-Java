<%-- 
    Document   : gerenciarestados
    Created on : 30/08/2021, 13:33:55
    Author     : Tassia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="../assets/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
        <meta charset="UTF-8">
        <title>Gerenciar Estados</title>
    </head>

    <body>
        <div class="row">
            <div class="col-sm-3 bg-dark text-white"><jsp:include page="../menu.jsp" /></div>          
            <div class="col-sm-9">
                <div class="container">
                    <div class="row">                       
                        <div class="col-sm-6">
                            <h1>Gerenciar Estados</h1>
                            <!--contextPath retorna a raiz do projeto-->
                            <form action="${pageContext.request.contextPath}/SalvarEstado">
                                <label for="id">ID</label>
                                <input  class="form-control" type="number" name="id" value="${oEstado.idEstado}" readonly/>
                                <br />
                                <label for="nome">Nome</label>
                                <input  class="form-control" type="text" name="nome" value="${oEstado.nomeEstado}"/>
                                <br />
                                <label for="sigla">Sigla</label>
                                <input  class="form-control" type="text" name="sigla" value="${oEstado.siglaEstado}"/>
                                <br />            
                                <input  class="btn btn-primary" type="submit" value="Salvar"/>
                            </form>  
                            ${mensagem}
                        </div>


                        <div class="col-sm-3"></div>
                    </div>

                    <div class="container mt-3">
                        <h2>Lista de Estados - Utilizando cards</h2>

                        <div class="container-fluid d-flex flex-wrap">
                            <c:forEach var="estado" items="${listaestados}" >                  
                                <div class="col-sm-3 px-2">
                                    <div class="card">
                                        <div class="card-header bg-dark text-white">
                                            <h4>${estado.nomeEstado}</h4>
                                        </div>
                                        <div class="card-body">
                                            <p>${estado.idEstado}</p>
                                            <p>${estado.nomeEstado}</p>
                                            <p>${estado.siglaEstado}</p>
                                        </div> 
                                        <div class="card-footer bg-dark text-white"> 
                                            <a class="btn btn-danger" href="ExcluirEstado?excluirEstado=${estado.idEstado}">Excluir</a>
                                            <a class="btn btn-primary" href="CarregarEstado?carregarEstado=${estado.idEstado}">Alterar</a>
                                        </div>
                                    </div>
                                </div>         
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
    </body>
</html>
