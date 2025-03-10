package com.FESA.validador.automato;

import org.springframework.stereotype.Service;

@Service
public class CpfAutomato {

    public boolean validarCPF(String cpf) {
        if (cpf == null || cpf.isEmpty()) return false;

        // Remove caracteres não numéricos
        String numeros = cpf.replaceAll("\\D", "");

        // Verifica se tem 11 dígitos
        if (numeros.length() != 11) return false;

        // Verifica se todos os dígitos são iguais (ex: 111.111.111-11) → CPF inválido
        if (numeros.matches("(\\d)\\1{10}")) return false;

        return validarComAutômato(numeros);
    }

    private boolean validarComAutômato(String cpf) {
        int estado = 0;
        int soma1 = 0, soma2 = 0;
        int peso1 = 10, peso2 = 11;
        int primeiroDigitoCalculado = -1, segundoDigitoCalculado = -1;
        int primeiroDigitoReal = -1, segundoDigitoReal = -1;

        for (int i = 0; i < cpf.length(); i++) {
            char c = cpf.charAt(i);
            int digito = c - '0';

            switch (estado) {
                case 0, 1, 2, 3, 4, 5, 6, 7, 8: // Processa os primeiros 9 dígitos
                    soma1 += digito * peso1--;
                    soma2 += digito * peso2--;
                    estado++;
                    break;
                case 9: // Último número antes do primeiro verificador
                    soma1 += digito * peso1--;
                    soma2 += digito * peso2--;
                    estado++;

                    // Cálculo do primeiro dígito verificador
                    primeiroDigitoCalculado = (soma1 % 11 < 2) ? 0 : (11 - soma1 % 11);
                    primeiroDigitoReal = digito;
                    break;
                case 10: // Verifica o primeiro dígito verificador
                    if (primeiroDigitoReal != primeiroDigitoCalculado) return false;
                    soma2 += digito * peso2--; // Acumula para o segundo verificador
                    estado++;

                    // Cálculo do segundo dígito verificador
                    segundoDigitoCalculado = (soma2 % 11 < 2) ? 0 : (11 - soma2 % 11);
                    segundoDigitoReal = digito;
                    break;
                case 11: // Verifica o segundo dígito verificador
                    if (segundoDigitoReal != segundoDigitoCalculado) return false;
                    estado = 12; // Estado final (CPF válido)
                    break;
                case 12: // Estado final (CPF válido)
                    return true;
                default:
                    return false;
            }
        }

        return estado == 12; // CPF só é válido se chegar ao estado final
    }
}
