package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Idioma;
import service.FilmeTO;

public class FilmeDAO {
	
		
	public void incluir(FilmeTO to) {
		String sqlInsert = "INSERT INTO Filme(id, nome, genero) VALUES (?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
		PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
				stm.setInt(1, to.getId());
				stm.setString(2, to.getNome());
				stm.setString(3, to.getGenero());
				stm.execute();
				
				for (Idioma idioma : to.getIdioma()) {
					incluirFilmeIdioma(to.getId(), idioma.getId());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	public ArrayList<Idioma> CarregarIdiomas(int idFilme){
		Idioma idioma;
		ArrayList<Idioma> lista = new ArrayList<>();
		String sqlSelect = "SELECT i.id, i.nome FROM idioma i inner join filme_idioma fi on fi.idIdioma = i.id where fi.idFilme = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			 stm.setInt(1,idFilme);
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					idioma = new Idioma();
					idioma.setId(rs.getInt("id"));
					idioma.setNome(rs.getString("nome"));
					lista.add(idioma);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}
	
	public void incluirFilmeIdioma(int idFilme, int idIdioma) {
		String sqlInsert = "INSERT INTO Filme_Idioma(idFilme, idIdioma) VALUES (?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
		PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
				stm.setInt(1, idFilme);
				stm.setInt(2, idIdioma);
				stm.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	public void excluirIdioma(int idFilme) {
		String sqlDelete = "DELETE FROM Filme_Idioma WHERE idFilme = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, idFilme);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	// BUSCAR por ID
	public FilmeTO carregar(int id) {
		FilmeTO filme = new FilmeTO();

		String sqlSelect = "SELECT nome, genero FROM Filme WHERE filme.id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, id);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					filme.setId(id);
					filme.setNome(rs.getString("nome"));
					filme.setGenero(rs.getString("genero"));
					filme.setIdioma(CarregarIdiomas(id));
				} else {
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return filme;
	}

	// LISTAR todos Livros
	public ArrayList<FilmeTO> listar() {
		FilmeTO filme;
		ArrayList<FilmeTO> lista = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, genero FROM filme";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			// stm.setInt(1,id);
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					filme = new FilmeTO();
					filme.setId(rs.getInt("id"));
					filme.setNome(rs.getString("nome"));
					filme.setGenero(rs.getString("genero"));
					filme.setIdioma(CarregarIdiomas(filme.getId()));
					lista.add(filme);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}
	
	public ArrayList<FilmeTO> listarIdioma(int id) {
		FilmeTO filme;
		ArrayList<FilmeTO> lista = new ArrayList<>();
		String sqlSelect = "SELECT f.id, f.nome, f.genero FROM filme f inner join filme_idioma fi on fi.idFilme = f.id where fi.idIdioma = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			 stm.setInt(1,id);
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					filme = new FilmeTO();
					filme.setId(rs.getInt("id"));
					filme.setNome(rs.getString("nome"));
					filme.setGenero(rs.getString("genero"));
					filme.setIdioma(CarregarIdiomas(filme.getId()));
					lista.add(filme);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}

	// EXCLUIR
	public void excluir(int id) {
		String sqlDelete = "DELETE FROM filme WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		excluirIdioma(id);
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ATUALIZAR
	public void atualizar(FilmeTO filme) {
		String sqlUpdate = "UPDATE filme SET nome=?, genero=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, filme.getNome());
			stm.setString(2, filme.getGenero());
			stm.setInt(3, filme.getId());
			stm.execute();
			excluirIdioma(filme.getId());
			for (Idioma idioma : filme.getIdioma()) {
				incluirFilmeIdioma(filme.getId(), idioma.getId());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
