package JPAPuro;

import Classes.Aluno;
import Classes.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainJPAPuro {
    public static void main(String[] args) {

        // Estamos apenas demonstrando, ainda não há nenhuma implementação até este ponto
        // Utilizamos JPA puro para demonstrar que através dele é possível definir a estrutura do código e depois escolhe a implementação que ira utilizar

        // Criar um gerenciador de entidades com o banco de dados especificado no arquivo "persistence.xml"
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("somente-JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Cria instâncias para serem adicionadas no banco de dados
        Estado estadoParaAdicionar = new Estado("Rio Grande do Sul", "RS");
        Aluno alunoParaAdicionar = new Aluno("Márcio", 37, estadoParaAdicionar);

        // Inicia uma transação para adicionar as instâncias no banco de dados
        entityManager.getTransaction().begin();

        entityManager.persist(estadoParaAdicionar);
        entityManager.persist(alunoParaAdicionar);

        entityManager.getTransaction().commit();

        // Encerra o gerenciador de entidades e encerrar a fabrica de gerenciadores de entidade
        entityManager.close();
        entityManagerFactory.close();

    }
}
