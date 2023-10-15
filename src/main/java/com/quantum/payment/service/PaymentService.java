package com.quantum.payment.service;
//author : Jere

import com.quantum.payment.entity.Payment;
import com.quantum.payment.repo.PaymentRepo;
import com.quantum.payment.response.PaymentResponse;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private ModelMapper mapper;


    //getById
    @Transactional
    public Payment getPaymentById(int id) {
        Payment payment = paymentRepo.findPaymentById(id);
        if (payment != null) {
            return payment;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "DATA TIDAK DITEMUKAN");
        }
    }


    //Get All
    public List<PaymentResponse> getAllPayments() {
        List<Payment> payments = paymentRepo.getAllPayments();

        return payments.stream()
                .map(payment -> mapper.map(payment, PaymentResponse.class))
                .collect(Collectors.toList());
    }

    //Post
    public Payment createPayment(Payment payment) {
        if (paymentRepo.findPaymentById(payment.getId()) != null) {
            throw new IllegalArgumentException("Payment with ID " + payment.getId() + " already exists.");
        }


        return paymentRepo.save(payment);
    }

    //Update
    public Payment updatePayment(int id, Payment updatedPayment) {
        Payment existingPayment = paymentRepo.findPaymentById(id);
        if (existingPayment != null) {
            existingPayment.setBank(updatedPayment.getBank());
            existingPayment.setCity(updatedPayment.getCity());
            existingPayment.setState(updatedPayment.getState());

            return paymentRepo.save(existingPayment);
        } else {
            throw new IllegalArgumentException("Payment not found with id: " + id);
        }
    }

    //Delete
    public void deletePayment(int id) {
        Payment payment = paymentRepo.findPaymentById(id);
        if (payment != null) {
            paymentRepo.delete(payment);
        } else {
            throw new IllegalArgumentException("Payment not found with id: " + id);
        }
        paymentRepo.deletePaymentById(id);
    }

}
