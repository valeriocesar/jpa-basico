package JPQL;

import Classes.Aluno;
import Classes.Aluno_;
import Classes.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class MainConsultaOrientadaObjetos {

    public static void main(String[] args) {

        // Dados instanciados para serem utilizados como exemplo
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com-implementacao");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Estado estadoParaAdicionar = new Estado("Minas Gerais", "MG");
        entityManager.persist(estadoParaAdicionar);
        entityManager.persist(new Estado("Bahia", "BA"));
        entityManager.persist(new Aluno("Valerio", 33, estadoParaAdicionar));
        entityManager.persist(new Aluno("Nayara", 35, estadoParaAdicionar));
        entityManager.persist(new Aluno("Liz", 2, estadoParaAdicionar));
        entityManager.getTransaction().commit();

        // Parâmetro que será utilizado nas próximas consultas
        String nome = "Nayara";

        // ----- Utilizando o método find do entityManager ----- //

//        Aluno alunoEntityManager = entityManager.find(Aluno.class, 1);
//
//        System.out.println("Consulta alunoEntityManager: " + alunoEntityManager);

        // ----- SQL nativo ----- //

//        String sql = "SELECT * FROM Aluno WHERE nome = :nome ";
//        Aluno alunoSQL = (Aluno) entityManager
//                .createNativeQuery(sql, Aluno.class)
//                .setParameter("nome", nome)
//                .getSingleResult();
//
//        String sqlList = "SELECT * FROM Aluno";
//        List<Aluno> alunoSQLList = entityManager
//                .createNativeQuery(sqlList, Aluno.class)
//                .getResultList();
//
//        System.out.println("Consulta alunoSQL: " + alunoSQL);
//        alunoSQLList.forEach(Aluno -> System.out.println("Consulta alunoSQLList: " + Aluno));

        // ----- JPQL ----- //

        String jpql = "select a from Aluno a where a.nome = :nome";
        Aluno alunoJPQL = entityManager
                .createQuery(jpql, Aluno.class)
                .setParameter("nome", nome)
                .getSingleResult();

        String jpqlList = "select a from Aluno a where a.estado = :estado";
        List<Aluno> alunoJPQLList = entityManager
                .createQuery(jpqlList, Aluno.class)
                .setParameter("estado", estadoParaAdicionar)
                .getResultList();

        System.out.println("Consulta alunoJPQL: " + alunoJPQL);
        alunoJPQLList.forEach(Aluno -> System.out.println("Consulta alunoJPQLList: " + Aluno));

        // ----- JPA Criteria API + JPA Metamodel ----- //

//        CriteriaQuery<Aluno> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Aluno.class);
//        Root<Aluno> alunoRoot = criteriaQuery.from(Aluno.class);
//        CriteriaBuilder.In<String> inClause = entityManager.getCriteriaBuilder().in(alunoRoot.get(Aluno_.nome));
//        inClause.value(nome);
//        criteriaQuery.select(alunoRoot).where(inClause);
//        Aluno alunoAPICriteria = entityManager.createQuery(criteriaQuery).getSingleResult();
//
//        CriteriaQuery<Aluno> criteriaQueryList = entityManager.getCriteriaBuilder().createQuery(Aluno.class);
//        Root<Aluno> alunoRootList = criteriaQueryList.from(Aluno.class);
//        List<Aluno> alunoAPICriteriaList = entityManager.createQuery(criteriaQueryList).getResultList();
//
//        System.out.println("Consulta alunoAPICriteria: " + alunoAPICriteria);
//        alunoAPICriteriaList.forEach(Aluno -> System.out.println("Consulta alunoAPICriteriaList: " + Aluno));

    }

}

