package pack;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("relation");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Team team = new Team();
            team.setName("week4");
            em.persist(team);

            Member member = new Member();
            member.setName("eun jin");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("team.getId() :" + team.getId());

            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();
            System.out.println("findTeam :" + findTeam.getName());

            tx.commit();
        } catch (Exception e) {
            System.out.println("rollback");

            tx.rollback();
        } finally {
            System.out.println("closing");

            em.close();
        }

        emf.close();

    }

}
