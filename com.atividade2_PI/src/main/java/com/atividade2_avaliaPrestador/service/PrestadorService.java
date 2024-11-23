package com.atividade2_avaliaPrestador.service;

import com.atividade2_avaliaPrestador.model.PrestadorEntity;
import com.atividade2_avaliaPrestador.repository.PrestadorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Reginaldo Gaspar
 */
@Service
public class PrestadorService {

    @Autowired
    PrestadorRepository prestadorRepository;

    public PrestadorEntity criarPrestador(PrestadorEntity p) {
        //f.setId(null);

        prestadorRepository.save(p);

        return p;
    }

    public PrestadorEntity atualizarPrestador(PrestadorEntity prestadorRequest, Integer id) {

        PrestadorEntity prestadorEncontrado = getPrestadorId(id);
        prestadorEncontrado.setNome(prestadorRequest.getNome());
        prestadorEncontrado.setServico(prestadorRequest.getServico());
        prestadorEncontrado.setCnpj(prestadorRequest.getCnpj());
        prestadorEncontrado.setTelefone(prestadorRequest.getTelefone());

        prestadorRepository.save(prestadorEncontrado);
        return prestadorEncontrado;
    }

    /*
    //método com retorno exception personalizado
    public FilmeEntity1 getFilmeId(Integer filmeId) {
        return filmeRepository.findById(filmeId).orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado " + filmeId));
    }*/
    //método com retorno padrão
    public PrestadorEntity getPrestadorId(Integer prestadorId) {
        return prestadorRepository.findById(prestadorId).orElse(null);
    }

    public List<PrestadorEntity> listarTodosPrestadores() {
        return prestadorRepository.findAll();

    }

    public void deletarPrestador(Integer prestadorId) {
        PrestadorEntity prestador = getPrestadorId(prestadorId);
        System.out.println("prestador id " + prestador.getId());
        prestadorRepository.deleteById(prestador.getId());
    }

}
