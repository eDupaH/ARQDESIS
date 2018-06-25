<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Buscar Filmes</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

</head>

<body>
	<!-- Modal -->
	<div class="modal fade" id="delete-modal" tabindex="-1" role="dialog"
		aria-labelledby="modalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Fechar">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="modalLabel">Excluir Filme</h4>
				</div>
				<div class="modal-body">Deseja realmente excluir este filme?</div>
				<div class="modal-footer">
					<form action="FilmeControler.do" method="post">
						<input type="hidden" name="id" id="id_excluir" />
						<button type="submit" class="btn btn-primary" name="command"
							value="DeletarFilme">Sim</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">N&atilde;o</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- /.modal -->
	<!-- Barra superior com os menus de navegação -->
	<c:import url="Menu.jsp" />
	<!-- Container Principal -->
	<div id="main" class="container">
		<form action="FilmeControler.do" method="post">
			<div id="top" class="row">
				<div class="col-md-3">
					<h2>Filmes</h2>
				</div>

				<div class="col-md-6">
					<div class="input-group h2">
						<select name="idioma" class="form-control">
							<c:forEach var="idioma" items="${idiomas}">
								<option value="${idioma.id }">${idioma.nome}</option>
							</c:forEach>
						</select> <span class="input-group-btn">
							<button class="btn btn-primary" type="submit" name="command"
								value="BuscarFilmesIdioma">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</span>
					</div>
				</div>

				<div class="col-md-3">
					<a href="FilmeControler.do?command=CriarFilmeTela"
						class="btn btn-primary pull-right h2">Novo Filme</a>
				</div>
			</div>
			<!-- /#top -->
		</form>
		<hr />
		<c:if test="${not empty filmes}">
			<div id="list" class="row">

				<div class="table-responsive col-md-12">
					<table class="table table-striped" cellspacing="0" cellpadding="0">
						<thead>
							<tr>
								<th>ID</th>
								<th>Nome</th>
								<th>Gênero</th>
								<th>Idioma</th>
								<th class="actions">Ações</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="filme" items="${filmes}">
								<tr>
									<td>${filme.id }</td>
									<td>${filme.nome }</td>
									<td>${filme.genero }</td>
									<td><c:forEach var="idioma" items="${filme.idioma }">
                                               	${idioma.nome };
                                               </c:forEach></td>
									<td class="actions"><a class="btn btn-success btn-xs"
										href="FilmeControler.do?command=VisualizarFilme&id=${filme.id }">Visualizar</a>
										<a class="btn btn-warning btn-xs"
										href="FilmeControler.do?command=EditarFilmeTela&id=${filme.id }">Editar</a>
										<a class="btn btn-danger btn-xs"
										href="FilmeControler.do?command=DeletarFilme&id=${filme.id }">Excluir</a>
									</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>

				</div>
			</div>
			<!-- /#list -->


		</c:if>
		<!-- /#bottom -->
	</div>
	<!-- /#main -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$("#delete-modal").on('show.bs.modal', function(event) {
			var button = $(event.relatedTarget); //botao que disparou a modal
			var recipient = button.data('filme');
			$("#id_excluir").val(recipient);
		});
	</script>
</body>

</html>