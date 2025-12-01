package sptech.projeto12.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/*
DTO para uso na entrada de dados.
Logo, seu sufixo poderia ser Request ou Input.
 */
public record AtualizacaoDonoEmailTelefoneRequest(
    @NotBlank @Size(min = 4, max = 40) String dono,
    @Email String email,
    String telefone // poderiamos adicionar validação aqui também
) {
}
