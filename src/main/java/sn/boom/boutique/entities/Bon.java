package sn.boom.boutique.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Représente un bon d'achat dans le système de la boutique.
 * Un bon est un document qui enregistre une transaction d'achat entre un client et la boutique
 * pour un produit spécifique.
 *
 * @author Boom
 * @version 1.0
 */
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Bon {
    
    /**
     * Identifiant unique du bon.
     * Généré automatiquement par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Numéro unique du bon suivant le format BON-XXXXXX.
     * Par exemple: BON-123456
     */
    @NotBlank(message = "Le numéro du bon est obligatoire")
    @Pattern(regexp = "^BON-[0-9]{6}$", message = "Le numéro du bon doit suivre le format BON-XXXXXX")
    @Column(unique = true)
    private String numero;

    /**
     * Date et heure de création du bon.
     * Initialisée automatiquement à la date et heure courantes.
     */
    @NotNull(message = "La date est obligatoire")
    private LocalDateTime date = LocalDateTime.now();

    /**
     * Montant total de la transaction.
     * Calculé en multipliant le prix unitaire du produit par la quantité.
     */
    @NotNull(message = "Le montant total est obligatoire")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le montant total doit être supérieur à 0")
    private BigDecimal montantTotal;
    
    /**
     * Client qui effectue l'achat.
     * Un bon doit toujours être associé à un client.
     */
    @NotNull(message = "Le client est obligatoire")
    @ManyToOne
    private Client client;
    
    /**
     * Produit concerné par le bon.
     * Un bon concerne un seul type de produit à la fois.
     */
    @NotNull(message = "Le produit est obligatoire")
    @ManyToOne
    private Produit produit;
    
    /**
     * Quantité de produits achetée.
     * Doit être au minimum de 1.
     */
    @NotNull(message = "La quantité est obligatoire")
    @Min(value = 1, message = "La quantité doit être au moins 1")
    private Integer quantite;
}