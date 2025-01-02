package sn.boom.boutique.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sn.boom.boutique.entities.Produit;
import sn.boom.boutique.exceptions.InsufficientStockException;
import sn.boom.boutique.exceptions.ResourceNotFoundException;
import sn.boom.boutique.impl.ProduitServiceImpl;
import sn.boom.boutique.repositories.ProduitRepository;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProduitServiceTest {

    @Mock
    private ProduitRepository produitRepository;

    @InjectMocks
    private ProduitServiceImpl produitService;

    private Produit produit;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        produit = new Produit();
        produit.setId(1L);
        produit.setNom("Produit Test");
        produit.setPrix(new BigDecimal("100.00"));
        produit.setQuantiteStock(10);
    }

    @Test
    void updateStock_Success() {
        when(produitRepository.findById(1L)).thenReturn(Optional.of(produit));
        when(produitRepository.save(any(Produit.class))).thenReturn(produit);

        Produit updatedProduit = produitService.updateStock(1L, 5);

        assertEquals(15, updatedProduit.getQuantiteStock());
        verify(produitRepository).save(any(Produit.class));
    }

    @Test
    void updateStock_InsufficientStock() {
        when(produitRepository.findById(1L)).thenReturn(Optional.of(produit));

        assertThrows(InsufficientStockException.class, 
            () -> produitService.updateStock(1L, -15));
    }

    @Test
    void getProduit_NotFound() {
        when(produitRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, 
            () -> produitService.getProduit(1L));
    }
}