package sn.boom.boutique.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le numéro de compte est obligatoire")
    @Pattern(regexp = "^[A-Z0-9]{10}$", message = "Le numéro de compte doit contenir 10 caractères alphanumériques")
    @Column(unique = true)
    private String numero;

    @NotNull(message = "Le solde est obligatoire")
    @DecimalMin(value = "0.0", message = "Le solde ne peut pas être négatif")
    private BigDecimal solde = BigDecimal.ZERO;
    
    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL)
    private List<Versement> versements;
}