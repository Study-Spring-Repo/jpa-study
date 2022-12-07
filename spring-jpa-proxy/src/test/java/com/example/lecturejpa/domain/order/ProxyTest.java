package com.example.lecturejpa.domain.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static com.example.lecturejpa.domain.order.OrderStatus.OPENED;
import static java.time.LocalDateTime.now;

@Slf4j
@SpringBootTest
public class ProxyTest {

    @Autowired
    EntityManagerFactory emf;

    private String uuid = UUID.randomUUID().toString();

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Order order = new Order();
        order.setUuid(uuid);
        order.setMemo("부재시 전화주세요.");
        order.setOrderDatetime(now());
        order.setOrderStatus(OPENED);

        em.persist(order);

        Member member = new Member();
        member.setName("kanghonggu");
        member.setAddress("서울시 동작구(만) 움직이면 쏜다.");
        member.setAge(33);
        member.setNickName("guppy.kang");
        member.setDescription("백앤드 개발자에요.");

        member.addOrder(order);
        em.persist(member);
        transaction.commit();
    }

    @Test
    void proxy() {
        EntityManager em = emf.createEntityManager();
        Order order = em.find(Order.class, uuid);

        Member member = order.getMember();
        log.info("MEMBER USE BEFORE IS-LOADED : {}", emf.getPersistenceUnitUtil().isLoaded(member)); // member 객체는 proxy 객체이다.

        // 이 때 실제 엔티티를 가져온다.
        String nickName = member.getNickName();// use
        log.info("MEMBER USE AFTER IS-LOADED : {}", emf.getPersistenceUnitUtil().isLoaded(member)); // member 객체가 entity이다.
    }

    @Test
    void move_persist() {
        EntityManager em = emf.createEntityManager();
        Order order = em.find(Order.class, uuid);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        OrderItem item = new OrderItem(); // 준영속 상태
        item.setQuantity(10);
        item.setPrice(100);

        order.addOrderItem(item);
        transaction.commit(); // flush
    }

    @Test
    void orphan() {
        EntityManager entityManager = emf.createEntityManager();

        // 회원 조회 -> 회원의 주문 까지 조회
        Member findMember = entityManager.find(Member.class, 1L);
        findMember.getOrders().remove(0); // order를 제거한다. (고아객체)

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        transaction.commit();
    }
}
