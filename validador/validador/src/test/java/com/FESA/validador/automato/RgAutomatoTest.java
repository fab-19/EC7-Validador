package com.FESA.validador.automato;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RgAutomatoTest {

    private final RgAutomato rgAutomato = new RgAutomato();

    @Test
    void testRgValido() {
        assertTrue(rgAutomato.validarRG("56.019.287-3"));
    }

    @Test
    void testRgValidoComX() {
        assertTrue(rgAutomato.validarRG("12.345.678-X"));
    }

    @Test
    void testRgInvalido() {
        assertFalse(rgAutomato.validarRG("56.019-287-3")); // Traço errado
        assertFalse(rgAutomato.validarRG("56.0192873"));   // Sem traço
        assertFalse(rgAutomato.validarRG("56A019.287-3")); // Letra no meio
    }
}
