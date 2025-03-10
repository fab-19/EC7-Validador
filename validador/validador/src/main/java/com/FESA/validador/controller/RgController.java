package com.FESA.validador.controller;

import com.FESA.validador.automato.RgAutomato;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RgController {

    private final RgAutomato rgAutomato;

    public RgController(RgAutomato rgAutomato) {
        this.rgAutomato = rgAutomato;
    }

    @GetMapping("/rg")
    public String rgForm(Model model) {
        model.addAttribute("rg", ""); // Garante que a variável existe
        model.addAttribute("resultado", "");
        return "rgForm";
    }

    @PostMapping("/rg")
    public String validarRG(@RequestParam String rg, Model model) {
        boolean valido = rgAutomato.validarRG(rg);
        model.addAttribute("resultado", valido ? "✅ RG válido!" : "❌ RG inválido!");
        model.addAttribute("rg", rg); // Mantém o valor do RG no campo de entrada
        return "rgForm";
    }
}
