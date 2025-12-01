package sptech.projeto12.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sptech.projeto12.model.Raca;
import sptech.projeto12.repository.RacaRespository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/racas")
@RequiredArgsConstructor
public class RacaController {

    private final RacaRespository racaRespository;

    @GetMapping("s-cache")
    public ResponseEntity<List<Raca>> obterRacasSCache() {
        return obterRacas();
    }

    @Cacheable("racas") // Aqui configuramos que o método é "cacheável"
    @GetMapping("c-cache")
    public ResponseEntity<List<Raca>> obterRacasCCache() {
        System.out.println("Obtendo raças +cache...");
        return obterRacas();
    }

    /*
    Esta anotação "LIMPA" o cache das raças quando uma raça é excluída
    Ou seja, quando uma raça for excluída, o cache é invalidado.
    Poderíamos colocar esta anotação no POST ou PUT também, caso houvesse
     */
    @CacheEvict(cacheNames = "racas", allEntries = true)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirRaca(String id) {
        if (!racaRespository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }
        racaRespository.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    /*
    Este método faz várias operações INUTEIS para simular um processamento demorado
     */
    private ResponseEntity<List<Raca>> obterRacas() {
        List<Raca> racas = racaRespository.findAll();
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
