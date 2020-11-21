package com.dilipkumarg.netent.bookstore.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "STOCK_ENTRY")
public class StockEntry extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "STOCK")
    private int stock;

    @ManyToOne
    @JoinColumn(name = "BOOK_STOCK_ID", referencedColumnName = "ID")
    private BookStock bookStock;
}
