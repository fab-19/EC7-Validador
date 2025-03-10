package com.FESA.validador.automato;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CpfAutomatoTest {

    @Test
    void testValidarCpf() {
        CpfAutomato cpfAutomato = new CpfAutomato();
        assertTrue(cpfAutomato.validarCPF("12345678909")); // Exemplo de CPF válido
        assertFalse(cpfAutomato.validarCPF("11111111111")); // Exemplo de CPF inválido
    }
}