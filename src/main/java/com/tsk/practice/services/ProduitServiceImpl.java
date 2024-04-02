package com.tsk.practice.services;

import com.tsk.practice.dtos.requests.ProduitRequestDto;
import com.tsk.practice.entities.Produit;
import com.tsk.practice.exceptions.NotFoundResourceException;
import com.tsk.practice.repositories.ProduitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;

    public ProduitServiceImpl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    public List<Produit> fetchAllProduits() {
        return produitRepository.findAll();
    }

    @Override
    public Produit getById(Integer id) {
        return produitRepository.findById(id).orElseThrow(()-> new NotFoundResourceException("Product", id));
    }

    @Override
    public Produit createProduit(ProduitRequestDto request) {
        Produit p = new Produit(null, request.getNom(), request.getPrix());
        return produitRepository.save(p);
    }

    @Override
    public Produit updateProduit(Integer id, ProduitRequestDto request) {
        Produit produit = produitRepository.findById(id).orElseThrow(()-> new NotFoundResourceException("Product", id));
        produit.setNom(request.getNom());
        produit.setPrix(request.getPrix());
        return produitRepository.save(produit);
    }

    @Override
    public void deleteById(Integer id) {
        Produit produit = produitRepository.findById(id).orElseThrow(()-> new NotFoundResourceException("Product", id));
        produitRepository.deleteById(produit.getId());
    }
}
