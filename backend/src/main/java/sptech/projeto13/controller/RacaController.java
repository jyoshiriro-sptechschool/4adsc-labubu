package sptech.projeto13.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.projeto13.model.Raca;
import sptech.projeto13.repository.RacaRespository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/racas")
@RequiredArgsConstructor
public class RacaController {

    private final RacaRespository racaRepository;

    @GetMapping("sem-cache")
    public ResponseEntity<List<Raca>> obterRacasSCache() {
        return obterRacas();
    }

    @Cacheable("racas") // Aqui configuramos que o método é "cacheável"
    @GetMapping
    public ResponseEntity<List<Raca>> obterRacasCCache() {
        System.out.println("Obtendo raças +cache...");
        return obterRacas();
    }

    @Cacheable("raca") // Aqui configuramos que o método é "cacheável"
    @GetMapping("/{codigo}")
    public ResponseEntity<Raca> obterRacaCCache(@PathVariable String codigo) {
        var racaOptional = racaRepository.findById(codigo);

        if (racaOptional.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(racaOptional.get());
    }

    /*
    Esta anotação "LIMPA" o cache das raças quando uma raça é excluída
    Ou seja, quando uma raça for excluída, o cache é invalidado.
    Poderíamos colocar esta anotação no POST ou PUT também, caso houvesse
     */
    @CacheEvict(cacheNames = {"racas", "raca"}, allEntries = true)
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> excluirRaca(String codigo) {
        if (!racaRepository.existsById(codigo)) {
            return ResponseEntity.status(404).build();
        }
        racaRepository.deleteById(codigo);
        return ResponseEntity.status(204).build();
    }

    @CacheEvict(cacheNames = {"racas", "raca"}, allEntries = true)
    @PostMapping
    public ResponseEntity<Raca> criarRaca(@RequestBody @Valid Raca novaRaca) {
        var racaSalva = racaRepository.save(novaRaca);
        return ResponseEntity.status(201).body(racaSalva);
    }

    @CacheEvict(cacheNames = {"racas", "raca"}, allEntries = true)
    @PostMapping("/{codigo}")
    public ResponseEntity<Raca> atualizarRaca(@PathVariable String codigo, @RequestBody @Valid Raca raca) {
        if (!racaRepository.existsById(codigo)) {
            return ResponseEntity.status(404).build();
        }
        var racaSalva = racaRepository.save(raca);
        return ResponseEntity.status(202).body(racaSalva);
    }

    /*
    Este método faz várias operações INUTEIS para simular um processamento demorado
     */
    private ResponseEntity<List<Raca>> obterRacas() {
        List<Raca> racas = racaRepository.findAll();
        racas.forEach(raca -> {
            System.out.println("Cod:" + raca.getCodigo() + " - Raça: " + raca.getNome());
            System.out.println(ThreadLocalRandom.current().nextDouble());
            try {
                System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(raca));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        try {
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(racas));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return racas.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(racas);
    }

}
