package kz.edu.astanait.bankingsystem.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "product_types")
public class ProductType {

    @Id
    @SequenceGenerator(
            name = "product_type_sequence",
            sequenceName = "product_type_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_type_sequence"
    )
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "name")
    private String name;
}
