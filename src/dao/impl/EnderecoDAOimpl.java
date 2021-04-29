package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.EnderecoDAO;
import model.Conta;
import model.Endereco;
import model.util.Conexao;

public class EnderecoDAOimpl implements EnderecoDAO {
	Conexao conexao = new Conexao();

	@Override
	public void salvar(Endereco endereco) {
		Connection conn = conexao.getConnection();
		
		String sql = "INSERT INTO ENDERECO (ID_ENDERECO, RUA, NUMERO, COMPLEMENTO)" + "VALUES ( ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, endereco.getId());
			ps.setString(2, endereco.getRua());
			ps.setInt(3, endereco.getNumero());
			ps.setString(4, endereco.getComplemento());
			ps.execute();
			System.out.println("ENDERECO INSERIDO COM SUCESSO");
			
		} catch (SQLException e) {
			System.out.println("ERRO AO INSERIR ENDERECO" + e.getMessage());
			
		}finally {
			conexao.fecharConexao(conn);
		}
	}

	@Override
	public void alterar(Endereco endereco) {
		Connection conn = conexao.getConnection();
		
		String sql = " UPDATE ENDERECO  RUA = ?, NUMERO = ?, COMPLEMENTO = ? WHERE ID_ENDERECO = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, endereco.getRua());
			ps.setInt(2, endereco.getNumero());
			ps.setString(3, endereco.getComplemento());
			ps.setInt(4, endereco.getId());
			ps.execute();
			
		} catch (Exception e) {
			System.out.println("ERRO AO ATUALIZAR ENDEREÇO");
			
		}finally {
			conexao.fecharConexao(conn);
		}
		
		
	}

	@Override
	public void remover(int id) {
		Connection conn = conexao.getConnection();

		String sql = "DELETE ENDERECO WHERE ID_ENDERECO =?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			System.out.println("ENDEREÇO DELETADO COM SUCESSO");

		} catch (Exception e) {
			System.out.println("ERRO AO DELETAR O ENDERECO" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
		
		
	}

	@Override
	public Endereco pesquisar(int id) {
		Connection conn = conexao.getConnection();
		Endereco endereco = new Endereco();
		String sql = " SELECT * FROM ENDERECO WHERE ENDERECO_ID = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				endereco.setId(rs.getInt("ID_ENDERECO"));
				endereco.setRua(rs.getString("RUA"));
				endereco.setNumero(rs.getInt("NUMERO"));
				endereco.setComplemento(rs.getString("COMPLEMENTO"));
				
			}
			
		} catch (Exception e) {
			System.out.println("ERRO AO PESQUISAR ENDEREÇO" +e.getMessage());
			
		}finally {
			conexao.fecharConexao(conn);
		}

		return endereco;
		
		
	}

	@Override
	public List<Endereco> ListarTodos() {
		Connection conn = conexao.getConnection();
		List<Endereco>retorno = new ArrayList<Endereco>();
		
		String sql ="SELECT * FROM ENDERECO";
				
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Endereco endereco = new Endereco();
				endereco.setId(rs.getInt("ID_ENDERECO"));
				endereco.setRua(rs.getString("RUA"));
				endereco.setNumero(rs.getInt("NUMERO"));
				endereco.setComplemento(rs.getString("COMPLEMENTO"));
				
				
			}
			
		} catch (Exception e) {
			System.out.println("ERRO AO LISTAR CONTA" + e.getMessage());
		
		} finally {
			conexao.fecharConexao(conn);
		}

		return retorno;
		
		
	}

	public int getSequence () {
		Connection conn = conexao.getConnection();
		Integer retorno = null;
		
		String sql = "select S_ID_ENDERECO.nextval into :NEW.ID_ENDERECO from dual";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				retorno = rs.getInt("SEQUENCE");
				
			}
		} catch (Exception e) {
			System.out.println("ERRO AO SEQUENCE" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
		return retorno;
		
	}

}
