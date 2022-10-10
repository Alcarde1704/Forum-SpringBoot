package com.br.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.forum.models.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{
  
  public Curso findByNome(String nomeCurso);

}
