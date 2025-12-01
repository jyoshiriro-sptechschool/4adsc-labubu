package sptech.projeto12.dto;

/*
Aqui criamos uma record de Projeção (Projection).
 */

public record PetSimplesResponseV2(
        Integer id,
        String nome,
        String dono,
        String raca
) {}
