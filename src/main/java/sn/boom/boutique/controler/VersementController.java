package sn.boom.boutique.controler;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.boom.boutique.entities.Compte;
import sn.boom.boutique.entities.Versement;
import sn.boom.boutique.services.VersementService;
import java.util.List;

@RestController
@RequestMapping("/api/versements")
@AllArgsConstructor
@CrossOrigin("*")
public class VersementController {
    private final VersementService versementService;

    @PostMapping
    public ResponseEntity<Versement> saveVersement(@RequestBody Versement versement) {
        return ResponseEntity.ok(versementService.saveVersement(versement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVersement(@PathVariable Long id) {
        versementService.deleteVersement(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Versement> getVersement(@PathVariable Long id) {
        return ResponseEntity.ok(versementService.getVersement(id));
    }

    @GetMapping
    public ResponseEntity<List<Versement>> getAllVersements() {
        return ResponseEntity.ok(versementService.getAllVersements());
    }

    @GetMapping("/compte")
    public ResponseEntity<List<Versement>> getVersementsByCompte(@RequestBody Compte compte) {
        return ResponseEntity.ok(versementService.getVersementsByCompte(compte));
    }
}