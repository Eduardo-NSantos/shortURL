package com.api.shortURL.payment;

import com.api.shortURL.payment.enums.PaymentStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String asaasPaymentId;

    @Column(nullable = false)
    private BigDecimal value;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatusEnum status;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private String billingType;

    private String pixEncodedImage;

    private String pixPayload;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate(){
        if (this.createdAt == null){
            this.createdAt = LocalDateTime.now();
        }
    }
}
