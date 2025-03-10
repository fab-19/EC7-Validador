package com.FESA.validador.automato;

import org.springframework.stereotype.Service;

@Service
public class RgAutomato {

    public boolean validarRG(String rg) {
        if (rg == null || rg.isEmpty()) return false;

        int estado = 0;
        int[] digitos = new int[9]; // Armazena os números do RG para validação
        int index = 0;
        String rgFormatado = rg.replaceAll("\\s+", ""); // Remove espaços em branco

        for (char c : rgFormatado.toCharArray()) {
            switch (estado) {
                case 0:
                    if (Character.isDigit(c)) {
                        estado = 1;
                        digitos[index++] = Character.getNumericValue(c);
                    } else return false;
                    break;
                case 1:
                    if (Character.isDigit(c)) {
                        estado = 2;
                        digitos[index++] = Character.getNumericValue(c);
                    } else return false;
                    break;
                case 2:
                    if (Character.isDigit(c)) {
                        estado = 3;
                        digitos[index++] = Character.getNumericValue(c);
                    } else if (c == '.') estado = 18;
                    else return false;
                    break;
                case 3:
                    if (Character.isDigit(c)) {
                        estado = 4;
                        digitos[index++] = Character.getNumericValue(c);
                    } else return false;
                    break;
                case 4:
                    if (Character.isDigit(c)) {
                        estado = 5;
                        digitos[index++] = Character.getNumericValue(c);
                    } else return false;
                    break;
                case 5:
                    if (Character.isDigit(c)) {
                        estado = 6;
                        digitos[index++] = Character.getNumericValue(c);
                    } else return false;
                    break;
                case 6:
                    if (Character.isDigit(c)) {
                        estado = 7;
                        digitos[index++] = Character.getNumericValue(c);
                    } else return false;
                    break;
                case 7:
                    if (Character.isDigit(c)) {
                        estado = 8;
                        digitos[index++] = Character.getNumericValue(c);
                    } else return false;
                    break;
                case 9:
                    return validarDigitoVerificador(digitos); // Validação matemática do RG
                case 16:
                    if (c == '-') estado = 8;
                    else return false;
                    break;
                case 18:
                    if (Character.isDigit(c)) {
                        estado = 19;
                        digitos[index++] = Character.getNumericValue(c);
                    } else return false;
                    break;
                case 19:
                    if (Character.isDigit(c)) {
                        estado = 11;
                        digitos[index++] = Character.getNumericValue(c);
                    } else return false;
                    break;
                case 11:
                    if (Character.isDigit(c)) {
                        estado = 12;
                        digitos[index++] = Character.getNumericValue(c);
                    } else return false;
                    break;
                case 12:
                    if (c == '.') estado = 13;
                    else return false;
                    break;
                case 13:
                    if (Character.isDigit(c)) {
                        estado = 14;
                        digitos[index++] = Character.getNumericValue(c);
                    } else return false;
                    break;
                case 14:
                    if (Character.isDigit(c)) {
                        estado = 15;
                        digitos[index++] = Character.getNumericValue(c);
                    } else return false;
                    break;
                case 15:
                    if (Character.isDigit(c)) {
                        estado = 16;
                        digitos[index++] = Character.getNumericValue(c);
                    } else return false;
                    break;
                case 8:
                    if (Character.isDigit(c)) {
                        estado = 9;
                        digitos[index++] = Character.getNumericValue(c);
                    } else if (c == 'X') {
                        estado = 10;
                        digitos[index++] = 10; // 'X' representa o dígito 10
                    } else return false;
                    break;
                case 10:
                    return validarDigitoVerificador(digitos);
            }
        }
        return estado == 9 || estado == 10; // Apenas aceita RGs que terminam nos estados corretos
    }

    private boolean validarDigitoVerificador(int[] digitos) {
        if (digitos.length < 9) return false; // Verifica se há 9 dígitos
        
        int soma = 0;
        int multiplicador = 2;
        
        // Cálculo do dígito verificador do RG
        for (int i = 7; i >= 0; i--) {
            soma += digitos[i] * multiplicador;
            multiplicador++;
        }
        
        int resto = soma % 11;
        int dvCalculado = (resto == 0 || resto == 1) ? 0 : 11 - resto;
        
        return digitos[8] == dvCalculado;
    }
}

