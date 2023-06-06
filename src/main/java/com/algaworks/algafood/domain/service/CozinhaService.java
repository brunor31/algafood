package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    public Cozinha buscar(Long id) {
        return cozinhaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Cozinha com o id %d não pode ser encontrada", id)));
    }

    public Cozinha adicionar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public Cozinha atualizar(Cozinha cozinha, Long id) {
        Cozinha cozinhaEntity = cozinhaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Não existe cadastro de cozinha com o id %d", id)));
        BeanUtils.copyProperties(cozinha, cozinhaEntity, "id");
        return cozinhaRepository.save(cozinhaEntity);
    }

    public void excluir(Long id) {
        try {
            cozinhaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cozinha com código %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso", id));
        }
    }
}
