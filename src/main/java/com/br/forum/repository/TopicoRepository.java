package com.br.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.forum.models.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{

  List<Topico> findByCurso_Nome(String nomeCurso);
  
}
