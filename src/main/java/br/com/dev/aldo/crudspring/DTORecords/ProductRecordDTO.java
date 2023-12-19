package br.com.dev.aldo.crudspring.DTORecords;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRecordDTO(
        String id,
        @NotBlank
        String nomeProduto,
        @NotNull
        Integer precoCentavos,
        boolean situation
) {
}
