package com.tsk.practice.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduitRequestDto {

    @NotBlank(message = "Le nom de produit est obligatoire")
    private String nom;
    @Min(value = 0, message = "Le prix doit être un nombre positif")
    private Float prix = 0F;
}
