package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cozinhas", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class CozinhaController {

    @Autowired
    private CozinhaService cozinhaService;
    @GetMapping
    public ResponseEntity<List<Cozinha>> listar(){
        List<Cozinha> cozinhas = cozinhaService.listar();
        return ResponseEntity.ok(cozinhas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id){
        Optional<Cozinha> cozinha = cozinhaService.buscar(id);
        if(cozinha.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cozinha.get());
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody Cozinha cozinha){
        cozinhaService.adicionar(cozinha);
    }

    @PutMapping("/{id}")
    public void atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha){
        
    }
}
