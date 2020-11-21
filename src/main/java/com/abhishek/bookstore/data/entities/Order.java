package com.abhishek.bookstore.data.entities;

import javax.persistence.*;

import com.abhishek.bookstore.data.models.OrderStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "BOOK_ORDER")
public class Order extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private String orderId;

    @Column(name = "BOOK_ISBN")
    private String bookIsbn;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "COST")
    private Double cost;

    @Column(name = "TOTAL")
    private Double total;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "BOOK_ISBN", referencedColumnName = "ISBN", insertable = false, updatable = false)
    private Book book;
}
