package sn.boom.boutique.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente un client dans le système.
 * Cette classe contient les informations personnelles d'un client, ainsi que les validations associées.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique du client

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 50, message = "Le nom doit contenir entre 2 et 50 caractères")
    private String nom; // Nom du client

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(min = 2, max = 50, message = "Le prénom doit contenir entre 2 et 50 caractères")
    private String prenom; // Prénom du client

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    @Column(unique = true)
    private String email; // Adresse email du client

    @Pattern(regexp = "^[0-9]{9}$", message = "Le numéro de téléphone doit contenir 9 chiffres")
    private String telephone; // Numéro de téléphone du client

    @Size(max = 255, message = "L'adresse ne peut pas dépasser 255 caractères")
    private String adresse; // Adresse du client
    
    @OneToOne(cascade = CascadeType.ALL)
    private Compte compte; // Compte associé au client
}