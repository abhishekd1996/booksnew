package com.abhishek.bookstore.data.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "BOOK_STOCK")
public class BookStock extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "BOOK_ISBN")
    private String bookIsbn;

    @Column(name = "TOTAL_STOCK")
    private int totalStock = 0;

    @Column(name = "ORDERED_STOCK")
    private int orderedStock = 0;

    @Version // optimistic locking
    @Column(name = "VERSION")
    private Long version = 0L;

    @ManyToOne
    @JoinColumn(name = "BOOK_ISBN", referencedColumnName = "ISBN", insertable = false, updatable = false)
    private Book book;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bookStock", cascade = CascadeType.ALL)
    private List<StockEntry> stockEntries = new ArrayList<>();

    public BookStock(final String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    @Transient
    public int getRemaining() {
        return totalStock - orderedStock;
    }
}
