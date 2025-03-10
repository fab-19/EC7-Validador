package com.FESA.validador.controller;

import com.FESA.validador.service.DocumentoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documento")
public class DocumentoController {

    private final DocumentoService documentoService;

    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    @GetMapping("/validar-cpf")
    public boolean validarCpf(@RequestParam String cpf) {
        return documentoService.validarCpf(cpf);
    }

    @GetMapping("/validar-rg")
    public boolean validarRg(@RequestParam String rg) {
        return documentoService.validarRg(rg);
    }
}