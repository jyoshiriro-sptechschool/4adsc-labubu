package sptech.projeto12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.projeto12.model.Regras;

public interface RegrasRepository extends JpaRepository<Regras, Integer> {

    // opcional: criar um finder que traz o único registro de regras
    Regras findTop1By();
}
