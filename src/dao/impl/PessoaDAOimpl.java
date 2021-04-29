package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PessoaDAO;
import model.Conta;
import model.Endereco;
import model.Pessoa;
import model.util.Conexao;

public class PessoaDAOimpl implements PessoaDAO{
	Conexao conexao = new Conexao();

	@Override
	public void salvar(Pessoa pessoa) {
    Connection conn = conexao.getConnection();
		
		String sql = "INSERT INTO PESSOA (NOME, IDADE, SEXO, CPF)" + "VALUES ( ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, pessoa.getNome());
			ps.setInt(2, pessoa.getIdade());
			ps.setString(3, pessoa.getSexo());
			ps.setString(4, pessoa.getCpf());
			System.out.println("PESSOA INSERIDA COM SUCESSO");
			
		} catch (SQLException e) {
			System.out.println("ERRO AO INSERIR PESSOA" + e.getMessage());
			
		}finally {
			conexao.fecharConexao(conn);
		}
		
	}

	@Override
	public void alterar(Pessoa pessoa) {
    Connection conn = conexao.getConnection();
		
		String sql = " UPDATE PESSOA  NOME = ?, IDADE = ?, SEXO = ?, CPF = ?  WHERE CPF = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, pessoa.getNome());
			ps.setInt(2, pessoa.getIdade());
			ps.setString(3, pessoa.getSexo());
			ps.setString(4, pessoa.getCpf());
			ps.execute();
			
		} catch (Exception e) {
			System.out.println("ERRO AO ATUALIZAR PESSOA");
			
		}finally {
			conexao.fecharConexao(conn);
		}
		
		
	}

	@Override
	public void remover(String cpf) {
		Connection conn = conexao.getConnection();

		String sql = "DELETE PESSOA WHERE CPF =?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cpf);
			ps.execute();
			System.out.println("PESSOA DELETADA COM SUCESSO");

		} catch (Exception e) {
			System.out.println("ERRO AO DELETAR PESSOA" + e.getMessage());
		} finally {
			conexao.fecharConexao(conn);
		}
		
		
	}

	@Override
	public Pessoa pesquisar(String cpf) {
		Connection conn = conexao.getConnection();
		Pessoa pessoa = new Pessoa();
		String sql = " SELECT * FROM PESSOA WHERE PESSOA = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cpf);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				pessoa.setCpf(rs.getString("CPF"));
				pessoa.setNome(rs.getString("NOME"));
				pessoa.setIdade(rs.getInt("IDADE"));
				pessoa.setSexo(rs.getString("SEXO"));
				
				
			}
			
		} catch (Exception e) {
			System.out.println("ERRO AO PESQUISAR PESSOA" +e.getMessage());
			
		}finally {
			conexao.fecharConexao(conn);
		}

		return pessoa;
		
	}

	@Override
	public List<Pessoa> ListarTodos() {
		Connection conn = conexao.getConnection();
		List<Pessoa>retorno = new ArrayList<Pessoa>();
		
		String sql ="SELECT * FROM PESSOA";
				
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setCpf(rs.getString("CPF"));
				pessoa.setNome(rs.getNString("NOME"));
				pessoa.setSexo(rs.getString("SEXO"));
				pessoa.setIdade(rs.getInt("IDADE"));
			
				
				
			}
			
		} catch (Exception e) {
			System.out.println("ERRO AO LISTAR PESSOA" + e.getMessage());
		
		} finally {
			conexao.fecharConexao(conn);
		}

		return retorno;
		
	}

	

}
