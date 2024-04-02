package com.tsk.practice.services;

import com.tsk.practice.dtos.requests.ProduitRequestDto;
import com.tsk.practice.entities.Produit;
import com.tsk.practice.repositories.ProduitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProduitServiceImplTest {

    @InjectMocks
    ProduitServiceImpl produitService;

    @Mock
    ProduitRepository produitRepository;


    @Test
    void fetchAllProduits() {
        //given
        Produit mockProduit = createMockProduitOne();
        when(produitRepository.findAll()).thenReturn(List.of(mockProduit));

        //when
        List<Produit> produits = produitService.fetchAllProduits();

        //then
        verify(produitRepository, times(1)).findAll();
        assertFalse(produits.isEmpty());
    }

    @Test
    void createProduit() {
        //given
        ProduitRequestDto request = new ProduitRequestDto("Produit ONE", 14F);
        Produit mockProduit = createMockProduitOne();

        when(produitRepository.save(any(Produit.class))).thenReturn(mockProduit);

        Produit response = produitService.createProduit(request);

        //then
        verify(produitRepository, times(1)).save(any(Produit.class));
        assertNotNull(response);
    }

    @Test
    void updateProduit() {

        //given
        ProduitRequestDto request = new ProduitRequestDto("Produit TWO", 28F);
        Integer idRequest = 1;
        Produit mockProduitOne = createMockProduitOne();

        when(produitRepository.findById(idRequest)).thenReturn(Optional.of(mockProduitOne));

        produitService.updateProduit(idRequest, request);

        //then
        verify(produitRepository, times(1)).findById(idRequest);
        verify(produitRepository, times(1)).save(any(Produit.class));
    }


    @Test
    void deleteById() {
        //given
        Integer idRequest = 1;
        Produit mockProduitOne = createMockProduitOne();
        when(produitRepository.findById(idRequest)).thenReturn(Optional.of(mockProduitOne));

        //when
        produitService.deleteById(idRequest);

        //then
        verify(produitRepository).deleteById(idRequest);
    }

    private Produit createMockProduitOne(){
        return new Produit(1, "Produit ONE", 14F);
    }
}