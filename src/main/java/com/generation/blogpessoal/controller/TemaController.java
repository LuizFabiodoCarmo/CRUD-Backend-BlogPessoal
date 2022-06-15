package com.generation.blogpessoal.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Tema;
import com.generation.blogpessoal.repository.TemaRepository;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {


	@Autowired
	private TemaRepository temaRepository;
	
	@GetMapping
	public ResponseEntity<Iterable<Tema>> getAll(){
		return ResponseEntity.ok(temaRepository.findAll());
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Tema> getById(@PathVariable Long id){
		return temaRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<Tema>> getByDescricao(@PathVariable String descricao){
	   return ResponseEntity.ok(((TemaRepository) temaRepository).findAllByDescricaoContainingIgnoreCase(descricao));
	  
    }
	   
    @PostMapping
    public ResponseEntity<Tema> postTema(@Valid @RequestBody Tema tema){
 	  
    	return ResponseEntity.status(HttpStatus.CREATED).body(temaRepository.save(tema));
    }  
    
    @PutMapping
    	public ResponseEntity<Tema> putTema(@Valid @RequestBody Tema tema) {
    	
    		return temaRepository.findById(tema.getId())
    				.map(resposta -> {
    					return ResponseEntity.ok().body(temaRepository.save(tema));
    				})
    				.orElse(ResponseEntity.notFound().build());
	
    }	
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTema(@PathVariable Long id) {
		Optional <Tema> resposta = temaRepository.findById(id);
		if (resposta.isPresent()) {
			temaRepository.deleteById(id);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
    }
}
	
