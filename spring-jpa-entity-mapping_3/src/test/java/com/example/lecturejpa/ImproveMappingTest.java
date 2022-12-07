package com.example.lecturejpa;

import com.example.lecturejpa.domain.order.Food;
import com.example.lecturejpa.domain.order.Order;
import com.example.lecturejpa.domain.order.OrderStatus;
import com.example.lecturejpa.domain.parent.Parent;
import com.example.lecturejpa.domain.parent.ParentId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static java.time.LocalDateTime.now;

@Slf4j
@SpringBootTest
public class ImproveMappingTest {

    @Autowired
    private EntityManagerFactory emf;

    @Test
    void inheritance_test() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Food food = new Food();
        food.setPrice(1000);
        food.setStockQuantity(100);
        food.setChef("백종원");

        em.persist(food);

        transaction.commit();
    }

    @Test
    void mapped_super_class_test() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Order order = new Order();
        order.setUuid(UUID.randomUUID().toString());
        order.setOrderStatus(OrderStatus.OPENED);
        order.setMemo("---");
        order.setOrderDatetime(now());

        transaction.commit();
    }

    @Test
    void id_test() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Parent parent = new Parent();
        parent.setId1("id1");
        parent.setId2("id2");
        em.persist(parent);

        transaction.commit();

        Parent entity = em.find(Parent.class, new ParentId("id1", "id2"));
        log.info("{}, {}", entity.getId1(), entity.getId2());
    }
}
