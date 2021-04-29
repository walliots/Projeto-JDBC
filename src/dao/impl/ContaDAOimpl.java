package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ContaDAO;
import model.Conta;
import model.util.Conexao;

public class ContaDAOimpl implements ContaDAO {
	Conexao conexao = new Conexao();

	@Override
	public void salvar(Conta conta) {
		Connection conn = conexao.getConnection();
		String sql = "INSERT INTO CONTA(NUMERO, LIMITE, SALDO) " + "VALUES(?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, conta.getNumero());
			ps.setDouble(3, conta.getLimite());
			ps.setDouble(2, conta.getSaldo());
			ps.execute();
			System.out.println("conta inserida com sucesso");

		} catch (SQLException e) {
			System.out.println("ERRO AO SALVAR CONTA");

		} finally {
			conexao.fecharConexao(conn);
		}

	}

	@Override
	public void alterar(Conta conta) {
		Connection conn = conexao.getConnection();

		String sql = "UPDATE CONTA SET SALDO = ? , LIMITE = ? WHERE NUMERO = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, conta.getSaldo());
			ps.setDouble(2, conta.getLimite());
			ps.setInt(3, conta.getNumero());
			ps.executeUpdate();
			System.out.println("CONTA ATUALIZADA COM SUCESSO");

		} catch (Exception e) {
			System.out.println("ERRO AO ATUALIZAR CONTA NO BANCO");
		} finally {
			conexao.fecharConexao(conn);
		}

	}

	@Override
	public void remover(int numero) {
		Connection conn = conexao.getConnection();

		String sql = "DELETE CONTA WHERE NUMERO =?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, numero);
			ps.execute();
			System.out.println("CONTA DELETADA COM SUCESSO");

		} catch (Exception e) {
			System.out.println("ERRO AO DELETAR A CONTA" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}

	}

	@Override
	public Conta pesquisar(int numero) {
		Connection conn = conexao.getConnection();
		Conta conta = new Conta();
		String sql = " SELECT * FROM CONTA WHERE NUMERO = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, numero);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				conta.setNumero(rs.getInt("NUMERO"));
				conta.setSaldo(rs.getDouble("SALDO"));
				conta.setLimite(rs.getDouble("LIMITE"));
				
			}
			
		} catch (Exception e) {
			System.out.println("ERRO AO PESQUISAR CONTA" +e.getMessage());
			
		}finally {
			conexao.fecharConexao(conn);
		}

		return conta;
	}

	@Override
	public List<Conta> ListarTodos() {
		
		Connection conn = conexao.getConnection();
		List<Conta>retorno = new ArrayList<Conta>();
		
		String sql ="SELECT * FROM CONTA";
				
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Conta conta = new Conta();
				conta.setNumero(rs.getInt("NUMERO"));
				conta.setSaldo(rs.getDouble("SALDO"));
				conta.setLimite(rs.getDouble("LIMITE"));
				retorno.add(conta);
				
			}
			
		} catch (Exception e) {
			System.out.println("ERRO AO LISTAR CONTA" + e.getMessage());
		
		} finally {
			conexao.fecharConexao(conn);
		}

		return retorno;
	}

}
