package br.com.dev.aldo.crudspring.domain.product;

import br.com.dev.aldo.crudspring.DTORecords.ProductRecordDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity(name = "product")
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProductDomain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false)
    private String nomeProduto;

    @Column(name = "price_in_cents", nullable = false)
    private Integer precoCentavos;

    @Column(name = "situation", nullable = false)
    private Boolean situation;

    public ProductDomain(ProductRecordDTO productData){
        this.nomeProduto = productData.nomeProduto();
        this.precoCentavos = productData.precoCentavos();
        this.situation = true;
    }
}

