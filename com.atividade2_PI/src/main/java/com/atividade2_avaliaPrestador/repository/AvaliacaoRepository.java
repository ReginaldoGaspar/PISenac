
package com.atividade2_avaliaPrestador.repository;

import com.atividade2_avaliaPrestador.model.AvaliacaoEntity;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Reginaldo Gaspar
 */
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoEntity, Integer> {

    List<AvaliacaoEntity> findByPrestador_id(Integer id);   
}
