package sptech.projeto12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.projeto12.model.Raca;

public interface RacaRespository extends
        JpaRepository<Raca, String> {
}
