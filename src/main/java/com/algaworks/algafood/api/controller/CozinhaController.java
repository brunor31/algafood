package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class CozinhaController {

    @Autowired
    private CozinhaService cozinhaService;
    @GetMapping
    public ResponseEntity<List<Cozinha>> listar(){
        return ResponseEntity.ok(cozinhaService.listar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id){
        try {
            return ResponseEntity.ok(cozinhaService.buscar(id));
        } catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha){
        return cozinhaService.adicionar(cozinha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@RequestBody Cozinha cozinha, @PathVariable Long id){
        try {
            return ResponseEntity.ok(cozinhaService.atualizar(cozinha, id));
        } catch (EntidadeNaoEncontradaException e){
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id){
        try {
            cozinhaService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException | EntidadeEmUsoException e){
            return ResponseEntity.notFound().build();
        }
    }
}
