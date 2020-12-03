package br.com.prjCRUD.teste;

import java.util.Calendar;
import java.util.GregorianCalendar;

import br.com.prjCRUD.dao.PessoaDAO;
import br.com.prjCRUD.mode.Pessoa;


/**
 * Classe utilizada para testar os métodos do PessoaDAO.
 */
public class PessoaDAOTeste {
  public static void main(String[] args) throws Exception {
    Pessoa pessoa = new Pessoa();
    pessoa.setNome("Victor Henrique");
    Calendar data = new GregorianCalendar();
    data.set(Calendar.YEAR, 1992);
    data.set(Calendar.MONTH, 06);
    data.set(Calendar.DAY_OF_MONTH, 27);
    pessoa.setDataNascimento(data.getTime());
    pessoa.setEmail("victorjp2@gmail.com");

    PessoaDAO dao = new PessoaDAO();
    System.out.println("Salvando a pessoa: " + pessoa.getNome());
    pessoa = dao.criar(pessoa);

    pessoa.setNome("Victor Henrique Amaral");
    pessoa = dao.criar(pessoa);
    System.out.println("Alterando a pessoa: " + pessoa.getNome());

    Pessoa pessoa2 = dao.consultarPorId(pessoa.getId());
    System.out.println("Consultando: " + pessoa2.getNome());

    System.out.println("Removendo a pessoa: " + pessoa.getId());
    dao.remover(pessoa.getId());
  }
}
