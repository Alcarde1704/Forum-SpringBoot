package com.br.forum.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.br.forum.service.dto.DetalhesTopicoDto;
import com.br.forum.service.form.AtualizacaoTopicoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.forum.service.dto.TopicoDto;
import com.br.forum.service.form.TopicoForm;
import com.br.forum.models.Topico;
import com.br.forum.repository.CursoRepository;
import com.br.forum.repository.TopicoRepository;

@Service
public class TopicoService {

  @Autowired
  private TopicoRepository topicoRepository;

  @Autowired
  private CursoRepository cursoRepository;

  public List<TopicoDto> listaTopicos(String nomeCurso){

    if(nomeCurso == null){
      List<Topico> topicos = topicoRepository.findAll();
      return TopicoDto.converter(topicos);
    } else {
      List<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso);
      return TopicoDto.converter(topicos);
    }

  }

  public ResponseEntity<TopicoDto> cadastrar(TopicoForm topicoForm, UriComponentsBuilder uriBuilder){
    Topico topico = topicoForm.converter(cursoRepository);
    topicoRepository.save(topico);

    URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
    return ResponseEntity.created(uri).body(new TopicoDto(topico));
  }


  public ResponseEntity<DetalhesTopicoDto> detalhar(Long id) {
    Optional<Topico> topico = topicoRepository.findById(id);

    if (topico.isPresent()){
      return ResponseEntity.ok(new DetalhesTopicoDto(topico.get()));
    } else {
      return ResponseEntity.notFound().build();
    }


  }

  public ResponseEntity<TopicoDto> atualizar(Long id, AtualizacaoTopicoForm atualizacaoTopicoForm) {

    Optional<Topico> optional = topicoRepository.findById(id);

    if (optional.isPresent()){
      Topico topico = atualizacaoTopicoForm.atualizar(id, topicoRepository);
      return ResponseEntity.ok(new TopicoDto(topico));
    } else {
      return ResponseEntity.notFound().build();
    }




  }

  public ResponseEntity<?> remover(Long id) {

    Optional<Topico> optional = topicoRepository.findById(id);

    if (optional.isPresent()){
      topicoRepository.deleteById(id);
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }

  }
}
