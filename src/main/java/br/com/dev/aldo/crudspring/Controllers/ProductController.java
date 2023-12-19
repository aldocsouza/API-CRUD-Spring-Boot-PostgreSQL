package br.com.dev.aldo.crudspring.Controllers;

import br.com.dev.aldo.crudspring.DTORecords.ProductRecordDTO;
import br.com.dev.aldo.crudspring.Repositories.ProductRepository;
import br.com.dev.aldo.crudspring.domain.product.ProductDomain;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public ResponseEntity getAllProductFirst(){
        var allProducts = productRepository.findAll();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity registryProduct(@RequestBody @Valid ProductRecordDTO productData){
        ProductDomain product = new ProductDomain(productData);
        productRepository.save(product);
        return ResponseEntity.ok("Produto Cadastrado");
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateProduct(@RequestBody @Valid ProductRecordDTO productData){
        Optional<ProductDomain> optionalProduct = productRepository.findById(productData.id());
        if(optionalProduct.isPresent()) {
            ProductDomain product = optionalProduct.get();
            product.setNomeProduto(productData.nomeProduto());
            product.setPrecoCentavos(productData.precoCentavos());
            return ResponseEntity.ok(product);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id){
        productRepository.deleteById(id);
        return ResponseEntity.ok("Produto removido com sucesso!");
    }

}
