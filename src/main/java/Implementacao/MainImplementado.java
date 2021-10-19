package Implementacao;

import Classes.Aluno;
import Classes.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainImplementado {
    public static void main(String[] args) {

        // Utilizamos a implementação JPA que foi definida no "persistence.xml"

        // Criar um gerenciador de entidades com o banco de dados especificado no arquivo "persistence.xml"
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com-implementacao");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Cria instâncias para serem adicionadas no banco de dados
        Estado estadoParaAdicionar = new Estado("Minas Gerais", "MG");
        Aluno alunoParaAdicionar = new Aluno("Valerio", 34, estadoParaAdicionar);

        // Inicia uma transação para adicionar as instâncias no banco de dados
        entityManager.getTransaction().begin();

        entityManager.persist(estadoParaAdicionar);
        entityManager.persist(alunoParaAdicionar);

        entityManager.getTransaction().commit();

        // Resgata instâncias no banco de dados
        Estado estadoEncontrado = entityManager.find(Estado.class, 1);
        Aluno alunoEncontrado = entityManager.find(Aluno.class, 1);

        System.out.println(estadoEncontrado);
        System.out.println(alunoEncontrado);

        // Altera uma entidade
//        entityManager.getTransaction().begin();
//
//        alunoEncontrado.setNome("Valerio");
//        alunoEncontrado.setIdade(20);
//
//        entityManager.getTransaction().commit();

        // Remove uma entidade
//        entityManager.getTransaction().begin();
//
//        entityManager.remove(alunoEncontrado);
//
//        entityManager.getTransaction().commit();

        // Encerra o gerenciador de entidades e encerrar a fabrica de gerenciadores de entidade
        entityManager.close();
        entityManagerFactory.close();

    }
}
