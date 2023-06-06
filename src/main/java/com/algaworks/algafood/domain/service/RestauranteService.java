package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscar(Long id) {
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Restaurante com o id %d não pode ser encontrado", id)));
    }

    public Restaurante adicionar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Cozinha com o id %d não pode ser encontrada", cozinhaId)));
        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

    public Restaurante atualizar(Restaurante restaurante, Long id) {
        Restaurante restauranteEntity = restauranteRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Restaurante com o id %d não pode ser encontrado", id)));
        cozinhaRepository.findById(restaurante.getCozinha().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Cozinha com o id %d não pode ser encontrada", restaurante.getCozinha().getId())));
        BeanUtils.copyProperties(restaurante, restauranteEntity, "id");
        return restauranteRepository.save(restauranteEntity);
    }

    public void excluir(Long id){
        try {
            restauranteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de restaurante com o id %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Restaurante com id %d não pode ser removido, pois está em uso", id));
        }
    }

}
