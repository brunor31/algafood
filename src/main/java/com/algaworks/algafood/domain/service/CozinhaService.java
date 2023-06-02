package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Cozinha> listar(){
        return cozinhaRepository.findAll();
    }

    public Optional<Cozinha> buscar(Long id){
        return cozinhaRepository.findById(id);
    }

    public void adicionar(Cozinha cozinha){
        Cozinha cozinhaEntity = new Cozinha();
        cozinhaEntity.setNome(cozinha.getNome());
        cozinhaRepository.save(cozinhaEntity);
    }
}
