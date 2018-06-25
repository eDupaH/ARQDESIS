package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import service.IdiomaTO;

public class IdiomaDAO {
	
	public void incluir(IdiomaTO to) {
		String sqlInsert = "INSERT INTO idioma(id, nome) VALUES (?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
		PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
				stm.setInt(1, to.getId());
				stm.setString(2, to.getNome());
				stm.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	// BUSCAR por ID
	public IdiomaTO carregar(int id) {
		IdiomaTO idioma = new IdiomaTO();

		String sqlSelect = "SELECT nome FROM idioma WHERE idioma.id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, id);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					idioma.setNome(rs.getString("nome"));
				} else {
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return idioma;
	}

	// LISTAR todos Livros
	public ArrayList<IdiomaTO> listar() {
		IdiomaTO idioma;
		ArrayList<IdiomaTO> lista = new ArrayList<>();
		String sqlSelect = "SELECT id, nome FROM idioma";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			// stm.setInt(1,id);
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					idioma = new IdiomaTO();
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

	// EXCLUIR
	public void excluir(int id) {
		String sqlDelete = "DELETE FROM idioma WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ATUALIZAR
	public void atualizar(IdiomaTO idioma) {
		String sqlUpdate = "UPDATE idioma SET nome=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, idioma.getNome());
			stm.setInt(3, idioma.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
