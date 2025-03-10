package com.FESA.validador.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DocumentoServiceTest {

    @Test
    void testValidarCpf() {
        DocumentoService service = new DocumentoService();
        assertTrue(service.validarCpf("12345678909"));
        assertFalse(service.validarCpf("00000000000"));
    }

    @Test
    void testValidarRg() {
        DocumentoService service = new DocumentoService();
        assertTrue(service.validarRg("12345678"));
        assertFalse(service.validarRg("ABCDEFGH"));
    }
}