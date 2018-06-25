<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>LOCADORA - Adicionar Filme </title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
    <!-- Barra superior com os menus de navegação -->
	<c:import url="Menu.jsp"/>
    <!-- Container Principal -->
    <div id="main" class="container">
        <h3 class="page-header">Incluir Filme</h3>
        <!-- Formulario para inclusao de filmes -->
        <form action="FilmeControler.do" method="post">
            <!-- area de campos do form -->
            <div class="row">
                <div class="form-group col-md-3">
                    <label for="nome">ID</label>
                    <input type="text" class="form-control" name="id" id="id" required maxlength="100" placeholder="">
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label for="nome">Nome</label>
                    <input type="text" class="form-control" name="nome" id="nome" required maxlength="100" placeholder="">
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-6">
                    <label for="fone">Gênero</label>
                    <input type="tel" class="form-control" name="genero" id="genero" maxlength="15" placeholder="">
                </div>

                <div class="form-group col-md-6">
                	<label> Idioma</label>
                	<select name="idioma" multiple class="form-control">
                		<c:forEach var="idioma" items="${idiomas}">
                			<option value="${idioma.id }">${idioma.nome}</option>
                		</c:forEach>
                	</select>
                </div>
            </div>
            <hr />
            <div id="actions" class="row">
                <div class="col-md-12">
                    <button type="submit" class="btn btn-primary" name="command" value="CriarFilme">Salvar</button>
                    <a href="FilmeControler.do?command=BuscarFilmes" class="btn btn-default">Cancelar</a>
                </div>
            </div>
        </form>
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>