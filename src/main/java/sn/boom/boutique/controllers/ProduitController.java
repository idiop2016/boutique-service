package sn.boom.boutique.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.boom.boutique.entities.Produit;
import sn.boom.boutique.services.ProduitService;
import java.util.List;

@RestController
@RequestMapping("/api/produits")
@AllArgsConstructor
@CrossOrigin("*")
public class ProduitController {
    private final ProduitService produitService;

    @PostMapping
    public ResponseEntity<Produit> saveProduit(@RequestBody Produit produit) {
        return ResponseEntity.ok(produitService.saveProduit(produit));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @RequestBody Produit produit) {
        produit.setId(id);
        return ResponseEntity.ok(produitService.updateProduit(produit));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduit(@PathVariable Long id) {
        return ResponseEntity.ok(produitService.getProduit(id));
    }

    @GetMapping
    public ResponseEntity<List<Produit>> getAllProduits() {
        return ResponseEntity.ok(produitService.getAllProduits());
    }

    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<Produit>> getProduitsByCategorie(@PathVariable String categorie) {
        return ResponseEntity.ok(produitService.getProduitsByCategorie(categorie));
    }

    @GetMapping("/prix/{prix}")
    public ResponseEntity<List<Produit>> getProduitsByPrixLessThan(@PathVariable Double prix) {
        return ResponseEntity.ok(produitService.getProduitsByPrixLessThan(prix));
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<Produit> updateStock(@PathVariable Long id, @RequestParam int quantite) {
        return ResponseEntity.ok(produitService.updateStock(id, quantite));
    }
}