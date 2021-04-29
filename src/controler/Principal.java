package controler;

import java.util.ArrayList;
import java.util.List;

import dao.ContaDAO;
import dao.impl.ContaDAOimpl;
import model.Conta;

public class Principal {
	public static void main(String[] args) {

		Conta conta = new Conta();
		conta.setNumero(1001);
		conta.setLimite(4555d);
		conta.setSaldo(3222d);
		
		
		ContaDAO contaDao = new ContaDAOimpl();
		contaDao.alterar(conta);
		
		List<Conta> listaConta = contaDao.ListarTodos();
		for (Conta conta2 : listaConta) {
			System.out.println(conta2.toString());
			
		}
		
		
		
		
			
			
		}
		
		
		
		
	}


