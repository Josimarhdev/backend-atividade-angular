package app.controller;

import app.dto.LivroDTO;
import app.dto.PessoaDTO;
import app.entity.Livro;
import app.entity.Pessoa;
import app.repository.LivroRepository;
import app.repository.PessoaRepository;
import app.service.LivroService;
import app.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livro")
@CrossOrigin(origins = "http://localhost:4200")
public class LivroController {

  @Autowired
  private LivroService livroService;

  @Autowired
  private LivroRepository livroRepository;

  @GetMapping
  private ResponseEntity<List<LivroDTO>> listAll(){
    try {
      List<LivroDTO> lista = livroService.listAll();
      return new ResponseEntity<>(lista, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping
  private ResponseEntity<LivroDTO> save(@RequestBody LivroDTO livroDTO){
    try {
      LivroDTO livroSalva = livroService.save(livroDTO);
      return new ResponseEntity<>(livroSalva, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("erro")
  private ResponseEntity<List<LivroDTO>> exemploErro(){
    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
  }




  @DeleteMapping("/{id}")
  private ResponseEntity<LivroDTO> delete(@PathVariable("id")final long id){
    try{
      final Livro livro = this.livroRepository.findById(id).orElse(null);

      if (livro == null){
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
      }
      livroRepository.deleteById(id);
      return new ResponseEntity<>(null, HttpStatus.OK);
    }
    catch (RuntimeException e){
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }


}
