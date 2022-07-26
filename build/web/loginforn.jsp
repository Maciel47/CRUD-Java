<%-- 
    Document   : loginforn
    Created on : 14 de jun de 2022, 22:00:21
    Author     : Ghost
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login ADM</title>
        <link href="assets/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="d-flex flex-column flex-shrink-0 p-3 text-white bg-dark h-100">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <form action="LogarFornecedor" method="post">
                    <div>
                        <text>Digite as credenciais de FORNECEDOR:</text>
                    </div><br/>
                    <!-- Email input -->
                    <div class="form-outline mb-4">
                        <input type="text" id="form2Example1" class="form-control" name="login">
                        <label class="form-label" for="form2Example1">Login</label>
                    </div>

                    <!-- Password input -->
                    <div class="form-outline mb-4">
                        <input type="password" id="form2Example2" class="form-control" name="senha">
                        <label class="form-label" for="form2Example2">Senha</label>
                    </div>

                    <!-- 2 column grid layout for inline styling -->
                    <div class="row mb-4">
                        <input type="submit" value="Entrar" class="btn btn-primary btn-block mb-4">
                        <div class="col d-flex justify-content-center">
                            <!-- Checkbox -->
                            <div class="form-check">
                                <text>Matenha-me conectado</text>
                                <input class="form-check-input" type="checkbox" value="" id="form2Example31" checked />
                            </div>
                        </div>
                        
                        
                        
                        <div class="col">
                            <!-- Simple link -->
                            <a href="#!">Esqueceu a senha?</a>
                        </div>
                    </div>

                    <!-- Register buttons -->
                    <div class="text-center">
                        <p><a href="#!">Cadastre-se</a></p>                                       
                    </div>
                </form>
            </div>
            <div class="col-md-4"></div>
            ${mensagem}
        </div>


        <script src="assets/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
