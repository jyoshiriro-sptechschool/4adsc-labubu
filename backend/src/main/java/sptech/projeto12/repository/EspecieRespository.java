package sptech.projeto12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.projeto12.model.Especie;

public interface EspecieRespository extends
        JpaRepository<Especie, String> {
}
