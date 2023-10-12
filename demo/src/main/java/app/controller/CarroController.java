package app.controller;

import app.dto.CarroDTO;
import app.dto.LivroDTO;
import app.entity.Carro;
import app.entity.Livro;
import app.repository.CarroRepository;
import app.repository.LivroRepository;
import app.service.CarroService;
import app.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/carro")
@CrossOrigin(origins = "http://localhost:4200")
public class CarroController {

  @Autowired
  private CarroService carroService;

  @Autowired
  private CarroRepository carroRepository;

  @GetMapping
  private ResponseEntity<List<CarroDTO>> listAll(){
    try {
      List<CarroDTO> lista = carroService.listAll();
      return new ResponseEntity<>(lista, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping
  private ResponseEntity<CarroDTO> save(@RequestBody CarroDTO carroDTO){
    try {
      CarroDTO carroSalva = carroService.save(carroDTO);
      return new ResponseEntity<>(carroSalva, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("erro")
  private ResponseEntity<List<CarroDTO>> exemploErro(){
    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
  }




  @DeleteMapping("/{id}")
  private ResponseEntity<CarroDTO> delete(@PathVariable("id")final long id){
    try{
      final Carro carro = this.carroRepository.findById(id).orElse(null);

      if (carro == null){
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
      }
      carroRepository.deleteById(id);
      return new ResponseEntity<>(null, HttpStatus.OK);
    }
    catch (RuntimeException e){
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
  }
}
