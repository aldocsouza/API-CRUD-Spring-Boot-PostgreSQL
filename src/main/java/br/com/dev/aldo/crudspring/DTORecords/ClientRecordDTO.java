package br.com.dev.aldo.crudspring.DTORecords;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientRecordDTO(
        Long id,
        @NotBlank
        String cpf,
        @NotBlank
        String nome,
        @NotBlank
        String role,

        Boolean situation
) {
}
