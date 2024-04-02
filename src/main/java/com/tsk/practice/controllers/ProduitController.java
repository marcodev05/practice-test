package com.tsk.practice.controllers;

import com.tsk.practice.dtos.Response;
import com.tsk.practice.dtos.requests.ProduitRequestDto;
import com.tsk.practice.entities.Produit;
import com.tsk.practice.services.ProduitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(description = "Gestion des produits")
public class ProduitController {
    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @ApiOperation("Liste des produits")
    @GetMapping("/produits")
    public ResponseEntity<Response<List<Produit>>> fetchAllProduit() {
        Response<List<Produit>> response = new Response<>(produitService.fetchAllProduits())
                .status(HttpStatus.OK.value())
                .message("REQUEST EXECUTED SUCCESSFULLY")
                .has_error(false);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation("Récuperer un produit par son id")
    @GetMapping("/produits/{id}")
    public ResponseEntity<Response<Produit>> fetchProduitById(@PathVariable("id") Integer id) {
        Response<Produit> response = new Response<>(produitService.getById(id))
                .status(HttpStatus.OK.value())
                .message("REQUEST EXECUTED SUCCESSFULLY")
                .has_error(false);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation("Creer un nouveau produit")
    @PostMapping("/produits")
    public ResponseEntity<Response<Produit>> createProduit(@Valid @RequestBody ProduitRequestDto requestProduit) {
        Response<Produit> response = new Response<>(produitService.createProduit(requestProduit))
                .status(HttpStatus.CREATED.value())
                .message("REQUEST EXECUTED SUCCESSFULLY")
                .has_error(false);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation("Mettre à jour un produit par son id")
    @PutMapping("/produits/{id}")
    public ResponseEntity<Response<Produit>> updateProduit(@PathVariable("id") Integer id, @Valid @RequestBody ProduitRequestDto requestProduit) {
        Response<Produit> response = new Response<>(produitService.updateProduit(id, requestProduit))
                .status(HttpStatus.OK.value())
                .message("REQUEST EXECUTED SUCCESSFULLY")
                .has_error(false);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation("Supprimer un produit par son id")
    @DeleteMapping("/produits/{id}")
    public ResponseEntity<Response<String>> deleteProduitById(@PathVariable("id") Integer id) {
        produitService.getById(id);
        Response<String> response = new Response<>("PRODUCT DELETED ")
                .status(HttpStatus.OK.value())
                .message("Request successfully executed ")
                .has_error(false);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
