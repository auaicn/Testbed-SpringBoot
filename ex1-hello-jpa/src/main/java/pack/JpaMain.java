package pack;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

        // 애플리케이션 실행간에 딱 하나만 만들어야 한다.
        // per DB 하나.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 고객이나, 시나리오 별 트랜잭션마다 하나씩 만들어준다.
        // 스레드간에 절대 공유해서는 안된다. database connection 처럼 사용하고나서 버려야한다.
        EntityManager em =  emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        // code Start
        // read 를 제외한 데이터베이스 수정은 transaction 내에서 해야한다.
        // 여기에서의 transaction 은 DB 내부에 구현되어있는 transaction 보다는 한단계 높은 단계의 transaction 인거지
        tx.begin();

        try {
            for (int i=0;i<20;i++){
                Member newMember = new Member();
                newMember.setName("auaicn " + i);
                newMember.setId((long)i);
                em.persist(newMember);
            }

            // 그런데, 18 살 이상의 모든 회원들을 가져오는 등 고급 검색(통계) 기능을 사용하고 싶다면 JPQL 을 사용해야한다
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(10)
                    .setMaxResults(10)
                    .getResultList();

            for (Member mem: result) {
                // System.out.println("menber.name = " + mem.getName());
            }

            em.clear();

            System.out.println("\n========= level-1 caching Test =========\n");

            System.out.println("\n5th, before caching");
            Member _5thMember = em.find(Member.class,5L);

            // 1차 caching 확인
            System.out.println("\n5th(same), maybe cached");
            Member _5again = em.find(Member.class,5L);

            /* detach test 는 persistence.xml 의 hbm2ddl.auto option 을 create 로 해놔서 확인이 어려움 */
            System.out.println("\n========= Dirty Check Test =========\n");

            // 6번째 녀석 찾아와놓고 시작
            Member _6thMember = em.find(Member.class,6L);
            System.out.println("6번째 녀석 찾아와놓고 시작\n");

            // dirty check (clean case)
            System.out.println("6번째 녀석, dirty check (clean case)");
            em.persist(_6thMember);
            em.flush();

            // dirty check (dirty case)
            System.out.println("6번째 녀석, dirty check (dirty case)");
            _6thMember.setName(_6thMember.getName() + " i'm dirty");
            em.persist(_6thMember);
            em.flush();


            System.out.println("\n========= Persistence / Detach Test =========\n");
            Member persistentMember = em.find(Member.class,11L);
            Member detachedMember = em.find(Member.class,12L);
            System.out.println("11번째 : 영속 / 12번째 : 준영속 찾아놓고 시작");

            // 영속 상태
            System.out.println("\n영속 상태, 11번 녀석");
            persistentMember.setName("not detached. so changed!");
            em.flush();

            // 준영속 상태
            System.out.println("\n준영속 상태, 12번 녀석");
            em.detach(detachedMember);
            detachedMember.setName("not detached. so changed!");
            em.flush();

            System.out.println("\n======== now commit ========\n");

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();

    }

}
