package com.br.forum.service.form;

import com.br.forum.models.Curso;
import com.br.forum.models.Topico;
import com.br.forum.repository.CursoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class TopicoForm {
  
  @NotBlank @Length(min = 5, max = 100)
  private String titulo;
  @NotBlank @Length(min = 5)
  private String mensagem;
  @NotBlank @Length(min = 5, max = 100)
  private String nomeCurso;
  
  public String getTitulo() {
    return titulo;
  }
  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }
  public String getMensagem() {
    return mensagem;
  }
  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }
  public String getNomeCurso() {
    return nomeCurso;
  }
  public void setNomeCurso(String nomeCurso) {
    this.nomeCurso = nomeCurso;
  }
  public Topico converter(CursoRepository cursoRepository) {
    Curso curso = cursoRepository.findByNome(nomeCurso);
    return new Topico(titulo, mensagem, curso);
  }

  

}
