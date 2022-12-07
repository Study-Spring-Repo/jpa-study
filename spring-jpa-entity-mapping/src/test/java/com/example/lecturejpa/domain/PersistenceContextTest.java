package com.example.lecturejpa.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class PersistenceContextTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EntityManagerFactory emf;

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    void 저장() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Customer customer = new Customer(); // 비영속
        customer.setId(1L);
        customer.setFirstName("hyunseo");
        customer.setLastName("park");

        em.persist(customer); // 비영속 -> 영속 (영속화)
        transaction.commit(); // em.flush();
    }

    @Test
    void 조회_DB조회() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Customer customer = new Customer(); // 비영속
        customer.setId(1L);
        customer.setFirstName("hyunseo");
        customer.setLastName("park");

        em.persist(customer); // 비영속 -> 영속 (영속화)
        transaction.commit(); // em.flush();

        em.detach(customer); // 영속 -> 준영속
        Customer findCustomer = em.find(Customer.class, 1L);
        log.info("{} {}", findCustomer.getFirstName(), findCustomer.getLastName());

    }

    @Test
    void 조회_1차캐시_이용() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Customer customer = new Customer(); // 비영속
        customer.setId(1L);
        customer.setFirstName("hyunseo");
        customer.setLastName("park");

        em.persist(customer); // 비영속 -> 영속 (영속화)
        transaction.commit(); // em.flush();

        Customer findCustomer = em.find(Customer.class, 1L);
        log.info("{} {}", findCustomer.getFirstName(), findCustomer.getLastName());
    }

    @Test
    void 수정() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Customer customer = new Customer(); // 비영속
        customer.setId(1L);
        customer.setFirstName("hyunseo");
        customer.setLastName("park");

        em.persist(customer); // 비영속 -> 영속 (영속화)
        transaction.commit(); // em.flush();

        transaction.begin();
        customer.setFirstName("hy");
        customer.setLastName("p");
        transaction.commit();
    }

    @Test
    void 삭제() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Customer customer = new Customer(); // 비영속
        customer.setId(1L);
        customer.setFirstName("hyunseo");
        customer.setLastName("park");

        em.persist(customer); // 비영속 -> 영속 (영속화)
        transaction.commit(); // em.flush();

        transaction.begin();
        em.remove(customer);
        transaction.commit();;
    }
}
