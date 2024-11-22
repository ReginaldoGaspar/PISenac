package com.atividade2_avaliaPrestador.service;

import com.atividade2_avaliaPrestador.model.AvaliacaoEntity;
import com.atividade2_avaliaPrestador.repository.AvaliacaoRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Reginaldo Gaspar
 */
@Service
public class AvaliacaoService {

    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoEntity criarAvaliacao(AvaliacaoEntity a) {
        a.setId(0);// passar para null
        avaliacaoRepository.save(a);
        return a;
    }

    /*
    //método com retorno exception personalizado
    public FilmeEntity1 getFilmeId(Integer filmeId) {
        return filmeRepository.findById(filmeId).orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado " + filmeId));
    }*/
    //método com retorno padrão
    public AvaliacaoEntity getAvaliacaoId(Integer avaliacaoId) {
        return avaliacaoRepository.findById(avaliacaoId).orElse(null);
    }

    public List<AvaliacaoEntity> listarTodosAvaliacoes() {
        return avaliacaoRepository.findAll();
    }

    public void deletarAvaliacao(int avaliacaoId) {
        avaliacaoRepository.deleteAll(avaliacaoRepository.findByPrestador_id(avaliacaoId));       
    }
}
