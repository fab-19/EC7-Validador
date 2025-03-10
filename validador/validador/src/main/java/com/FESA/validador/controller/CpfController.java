package com.FESA.validador.controller;

import com.FESA.validador.automato.CpfAutomato;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CpfController {

    private final CpfAutomato cpfAutomato;

    public CpfController(CpfAutomato cpfAutomato) {
        this.cpfAutomato = cpfAutomato;
    }

    @GetMapping("/cpf")
    public String cpfForm(Model model) {
        model.addAttribute("cpf", ""); // Garante que a variável existe
        model.addAttribute("resultado", "");
        return "cpfForm";
    }

    @PostMapping("/cpf")
    public String validarCPF(@RequestParam String cpf, Model model) {
        boolean valido = cpfAutomato.validarCPF(cpf);
        model.addAttribute("resultado", valido ? "✅ CPF válido!" : "❌ CPF inválido!");
        model.addAttribute("cpf", cpf); // Mantém o valor do CPF no campo de entrada
        return "cpfForm";
    }
}
