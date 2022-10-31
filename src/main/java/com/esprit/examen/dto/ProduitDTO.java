package com.esprit.examen.dto;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.DetailFacture;
import com.esprit.examen.entities.Stock;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProduitDTO {
        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idProduit;
        private String codeProduit;
        private String libelleProduit;
        private float prix;
        @Temporal(TemporalType.DATE)
        private Date dateCreation;
        @Temporal(TemporalType.DATE)
        private Date dateDerniereModification;
        @ManyToOne
        @JsonIgnore
        private Stock stock;
        @OneToMany(mappedBy = "produit")
        @JsonIgnore
        private Set<DetailFacture> detailFacture;
        @ManyToOne
        @JsonIgnore
        private CategorieProduit categorieProduit;



    }
