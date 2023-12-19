package br.com.dev.aldo.crudspring.Controllers;

import br.com.dev.aldo.crudspring.DTORecords.ClientRecordDTO;
import br.com.dev.aldo.crudspring.DTORecords.ProductRecordDTO;
import br.com.dev.aldo.crudspring.Repositories.ClientRepository;
import br.com.dev.aldo.crudspring.domain.product.ClientDomain;
import br.com.dev.aldo.crudspring.domain.product.ProductDomain;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/find-all")
    public ResponseEntity getAllUser(){
        var client = clientRepository.findAll();
        return ResponseEntity.ok(client);
    }

    @GetMapping("/find-all-actives")
    public ResponseEntity findAllActivesUsers(){
        var client = clientRepository.findBySituationTrue();
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity postNewUser(@RequestBody @Valid ClientRecordDTO clientData){
        ClientDomain newClient = new ClientDomain(clientData);
        clientRepository.save(newClient);
        return ResponseEntity.ok("Dados Cadastrados com sucesso!");
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateUsers(@RequestBody @Valid ClientRecordDTO clientData){
        Optional<ClientDomain> optionalClient = clientRepository.findById(clientData.id());
        if(optionalClient.isPresent()){
            ClientDomain updateClient = optionalClient.get();
            updateClient.setNome(clientData.nome());
            updateClient.setCpf(clientData.cpf());
            updateClient.setRole(clientData.role());
            return ResponseEntity.ok(updateClient);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id){
        clientRepository.deleteById(id);
        return ResponseEntity.ok("Cliente deletado com sucesso!");
    }

    @DeleteMapping("/desactive/{id}")
    @Transactional
    public ResponseEntity inactiveClient(@PathVariable Long id){
        Optional<ClientDomain> optionalClient = clientRepository.findById(id);
        if(optionalClient.isPresent()){
            ClientDomain client = optionalClient.get();
            client.setSituation(false);
            return ResponseEntity.ok("Alteração bem sucedida!");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/active/{id}")
    @Transactional
    public ResponseEntity activeClient(@PathVariable Long id){
        Optional<ClientDomain> optionalClient = clientRepository.findById(id);
        if(optionalClient.isPresent()){
            ClientDomain client = optionalClient.get();
            client.setSituation(true);
            return ResponseEntity.ok("Alteração bem sucedida!");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
