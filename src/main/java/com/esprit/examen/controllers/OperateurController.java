package com.esprit.examen.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.esprit.examen.entities.Operateur;
import com.esprit.examen.services.IOperateurService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "Gestion des opérateurs")
@RequestMapping("/operateur")
@CrossOrigin("*")
public class OperateurController {

	
}
