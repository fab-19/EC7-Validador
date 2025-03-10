package com.FESA.validador.service;

import org.springframework.stereotype.Service;

import com.FESA.validador.automato.CpfAutomato;
import com.FESA.validador.automato.RgAutomato;

@Service
public class DocumentoService {

    private final CpfAutomato cpfAutomato;
    private final RgAutomato rgAutomato;

    public DocumentoService() {
        this.cpfAutomato = new CpfAutomato();
        this.rgAutomato = new RgAutomato();
    }

    public boolean validarCpf(String cpf) {
        return cpfAutomato.validarCPF(cpf);
    }

    public boolean validarRg(String rg) {
        return rgAutomato.validarRG(rg);
    }
}