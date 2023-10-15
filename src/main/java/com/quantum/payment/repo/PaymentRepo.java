package com.quantum.payment.repo;
//author : Jere

import com.quantum.payment.entity.Payment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PaymentRepo extends CrudRepository<Payment, Integer> {

    @Query(value = "SELECT * FROM payment WHERE id = :id", nativeQuery = true)
    Payment findPaymentById(@Param("id") int id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE payment SET bank = :bank, city = :city, state = :state WHERE id = :id", nativeQuery = true)
    void updateEmployee(@Param("id") int id, @Param("bank") String bank, @Param("city") String city, @Param("state") String state);


    @Query(value = "SELECT * FROM payment", nativeQuery = true)
    List<Payment> getAllPayments();


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM payment WHERE id = :id", nativeQuery = true)
    void deletePaymentById(@Param("id") int id);
}
