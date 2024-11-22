
package com.atividade2_avaliaPrestador.repository;

import com.atividade2_avaliaPrestador.model.PrestadorEntity;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author Reginaldo Gaspar
 */
public interface PrestadorRepository extends JpaRepository<PrestadorEntity, Integer>  {   
  
    
}
