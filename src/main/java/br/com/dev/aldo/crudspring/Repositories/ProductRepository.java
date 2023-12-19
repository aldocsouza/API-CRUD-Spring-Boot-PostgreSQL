package br.com.dev.aldo.crudspring.Repositories;

import br.com.dev.aldo.crudspring.domain.product.ProductDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductDomain, String> {
}
