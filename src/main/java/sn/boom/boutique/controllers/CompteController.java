package sn.boom.boutique.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.boom.boutique.entities.Compte;
import sn.boom.boutique.services.CompteService;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/comptes")
@AllArgsConstructor
@CrossOrigin("*")
public class CompteController {
    private final CompteService compteService;

    @PostMapping
    public ResponseEntity<Compte> saveCompte(@RequestBody Compte compte) {
        return ResponseEntity.ok(compteService.saveCompte(compte));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compte> updateCompte(@PathVariable Long id, @RequestBody Compte compte) {
        compte.setId(id);
        return ResponseEntity.ok(compteService.updateCompte(compte));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompte(@PathVariable Long id) {
        compteService.deleteCompte(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compte> getCompte(@PathVariable Long id) {
        return ResponseEntity.ok(compteService.getCompte(id));
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<Compte> getCompteByNumero(@PathVariable String numero) {
        return ResponseEntity.ok(compteService.getCompteByNumero(numero));
    }

    @GetMapping
    public ResponseEntity<List<Compte>> getAllComptes() {
        return ResponseEntity.ok(compteService.getAllComptes());
    }

    @PostMapping("/crediter/{numero}")
    public ResponseEntity<Compte> crediterCompte(@PathVariable String numero, @RequestParam BigDecimal montant) {
        return ResponseEntity.ok(compteService.crediterCompte(numero, montant));
    }

    @PostMapping("/debiter/{numero}")
    public ResponseEntity<Compte> debiterCompte(@PathVariable String numero, @RequestParam BigDecimal montant) {
        return ResponseEntity.ok(compteService.debiterCompte(numero, montant));
    }
}