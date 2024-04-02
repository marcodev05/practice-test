package com.tsk.practice.services;

import com.tsk.practice.dtos.requests.ProduitRequestDto;
import com.tsk.practice.entities.Produit;

import java.util.List;

public interface ProduitService {
    List<Produit> fetchAllProduits();
    Produit getById(Integer id);
    Produit createProduit(ProduitRequestDto produitRequest);
    Produit updateProduit(Integer id, ProduitRequestDto produitRequest);
    void deleteById(Integer id);
}
