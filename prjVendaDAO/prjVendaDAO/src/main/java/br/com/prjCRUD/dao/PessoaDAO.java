package br.com.prjCRUD.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.prjCRUD.mode.Pessoa;


public class PessoaDAO {

    private EntityManager getEntityManager() {
    EntityManagerFactory factory = null;
    EntityManager entityManager = null;
    try {
      //Obtém o factory a partir da unidade de persistência.
      factory = Persistence.createEntityManagerFactory("ExemplosJPAPU");
      //Cria um entity manager.
      entityManager = factory.createEntityManager();
      //Fecha o factory para liberar os recursos utilizado.
    } finally {
      factory.close();
    }
    return entityManager;
  }

  /**
   * Método utilizado para salvar ou atualizar as informações de uma pessoa.
   */
  public Pessoa criar(Pessoa pessoa) throws Exception {
    EntityManager entityManager = getEntityManager();
    try {
      // Inicia uma transação com o banco de dados.
      entityManager.getTransaction().begin();
      System.out.println("Criando a pessoa.");
      // Verifica se a pessoa ainda não está  no banco de dados.
      if(pessoa.getId() == null) {
        //Salva os dados da pessoa.
        entityManager.persist(pessoa);
      } else {
        //Atualiza os dados da pessoa.
        pessoa = entityManager.merge(pessoa);
      }
      // Finaliza a transação.
      entityManager.getTransaction().commit();
    } finally {
      entityManager.close();
    }
    return pessoa;
  }

  /**
   * Método que apaga a pessoa do banco de dados.
   */
  public void remover(Long id) {
    EntityManager entityManager = getEntityManager();
    try {
      // Inicia uma transação com o banco de dados.
      entityManager.getTransaction().begin();
      // Consulta a pessoa na base de dados através do seu ID.
      Pessoa pessoa = entityManager.find(Pessoa.class, id);
      System.out.println("Excluindo os dados de: " + pessoa.getNome());
      // Remove a pessoa da base de dados.
      entityManager.remove(pessoa);
      // Finaliza a transação.
      entityManager.getTransaction().commit();
    } finally {
      entityManager.close();
    }
  }

  /**
   * Consulta o pessoa pelo ID.
   */
  public Pessoa consultarPorId(Long id) {
    EntityManager entityManager = getEntityManager();
    Pessoa pessoa = null;
    try {
      //Consulta uma pessoa pelo seu ID.
      pessoa = entityManager.find(Pessoa.class, id);
    } finally {
      entityManager.close();
    }
    return pessoa;
  }
}
