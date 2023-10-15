package com.quantum.payment.controller;
//author : Jere

import com.quantum.payment.entity.Payment;
import com.quantum.payment.response.PaymentResponse;
import com.quantum.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PaymentController {


    @Autowired
    private PaymentService paymentService;

    // getById
    @GetMapping("/payment/{id}")
    private ResponseEntity<Payment> getPaymentDetails(@PathVariable("id") int id) {
        Payment payment = paymentService.getPaymentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(payment);
    }

    //getAll
    @GetMapping("/payments/all")
    public ResponseEntity<List<PaymentResponse>> getAllPayments() {
        List<PaymentResponse> payments = paymentService.getAllPayments();
        return ResponseEntity.status(HttpStatus.OK).body(payments);
    }

    //post
    @PostMapping("/payment")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        Payment createdPayment = paymentService.createPayment(payment);
        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/payment/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable("id") int id, @RequestBody Payment updatedPayment) {
        Payment updated = paymentService.updatePayment(id, updatedPayment);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }


    @DeleteMapping("/payment/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable("id") int id) {
        paymentService.deletePayment(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
    }
}
