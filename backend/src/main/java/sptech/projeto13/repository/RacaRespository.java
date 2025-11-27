package sptech.projeto13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.projeto13.model.Raca;

public interface RacaRespository extends
        JpaRepository<Raca, String> {
}
