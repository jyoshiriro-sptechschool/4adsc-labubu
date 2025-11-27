package sptech.projeto13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.projeto13.model.Especie;

public interface EspecieRespository extends
        JpaRepository<Especie, String> {
}
