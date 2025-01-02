package sn.boom.boutique.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.boom.boutique.entities.Bon;
import sn.boom.boutique.entities.Client;
import sn.boom.boutique.services.BonService;
import java.util.List;

@RestController
@RequestMapping("/api/bons")
@AllArgsConstructor
@CrossOrigin("*")
public class BonController {
    private final BonService bonService;

    @PostMapping
    public ResponseEntity<Bon> saveBon(@RequestBody Bon bon) {
        return ResponseEntity.ok(bonService.saveBon(bon));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bon> updateBon(@PathVariable Long id, @RequestBody Bon bon) {
        bon.setId(id);
        return ResponseEntity.ok(bonService.updateBon(bon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBon(@PathVariable Long id) {
        bonService.deleteBon(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bon> getBon(@PathVariable Long id) {
        return ResponseEntity.ok(bonService.getBon(id));
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<Bon> getBonByNumero(@PathVariable String numero) {
        return ResponseEntity.ok(bonService.getBonByNumero(numero));
    }

    @GetMapping
    public ResponseEntity<List<Bon>> getAllBons() {
        return ResponseEntity.ok(bonService.getAllBons());
    }

    @GetMapping("/client")
    public ResponseEntity<List<Bon>> getBonsByClient(@RequestBody Client client) {
        return ResponseEntity.ok(bonService.getBonsByClient(client));
    }

    @PutMapping("/{id}/valider")
    public ResponseEntity<Bon> validerBon(@PathVariable Long id) {
        return ResponseEntity.ok(bonService.validerBon(id));
    }

    @PutMapping("/{id}/annuler")
    public ResponseEntity<Bon> annulerBon(@PathVariable Long id) {
        return ResponseEntity.ok(bonService.annulerBon(id));
    }
}