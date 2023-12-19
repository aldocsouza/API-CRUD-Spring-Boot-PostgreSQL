package br.com.dev.aldo.crudspring.Repositories;

import br.com.dev.aldo.crudspring.domain.product.ClientDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientDomain, Long> {
    List<ClientDomain> findBySituationTrue();
}
