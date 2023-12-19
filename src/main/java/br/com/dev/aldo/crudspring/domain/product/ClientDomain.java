package br.com.dev.aldo.crudspring.domain.product;

import br.com.dev.aldo.crudspring.DTORecords.ClientRecordDTO;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity(name = "client")
@Table(name = "client")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class ClientDomain implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    private String nome;

    private String role;

    private Boolean situation;

    public ClientDomain(ClientRecordDTO clientData){
        this.cpf = clientData.cpf();
        this.nome = clientData.nome();
        this.role = clientData.role();
        this.situation = true;
    }
}
