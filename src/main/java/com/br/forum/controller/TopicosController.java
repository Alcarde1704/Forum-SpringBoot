package com.br.forum.controller;


import java.util.List;

import com.br.forum.service.dto.DetalhesTopicoDto;
import com.br.forum.service.form.AtualizacaoTopicoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.forum.service.dto.TopicoDto;
import com.br.forum.service.form.TopicoForm;
import com.br.forum.service.TopicoService;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

  @Autowired
  private TopicoService topicoService;
  
  @GetMapping
  public List<TopicoDto> listaTopicos(String nomeCurso) {
      return this.topicoService.listaTopicos(nomeCurso);
  }

  @PostMapping
  @Transactional
  public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriBuilder){
      return this.topicoService.cadastrar(topicoForm, uriBuilder);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DetalhesTopicoDto> detalhar(@PathVariable Long id){
      return this.topicoService.detalhar(id);
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm atualizacaoTopicoForm){
      return this.topicoService.atualizar(id, atualizacaoTopicoForm);
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<?> remover(@PathVariable Long id){
      return this.topicoService.remover(id);
  }


}
