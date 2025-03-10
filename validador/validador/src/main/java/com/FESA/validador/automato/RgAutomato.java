package com.FESA.validador.automato;

import org.springframework.stereotype.Service;

@Service
public class RgAutomato {

    public boolean validarRG(String rg) {
        if (rg == null || rg.isEmpty()) return false;

        int estado = 0;
        String rgFormatado = rg.replaceAll("\\s+", ""); // Remove espaços em branco

        for (char c : rgFormatado.toCharArray()) {
            switch (estado) {
                case 0:
                    if (Character.isDigit(c)) estado = 1;
                    else return false;
                    break;
                case 1:
                    if (Character.isDigit(c)) estado = 2;
                    else if (c == '.') estado = 18;
                    else return false;
                    break;
                case 2:
                    if (Character.isDigit(c)) estado = 3;
                    else return false;
                    break;
                case 3:
                    if (Character.isDigit(c)) estado = 4;
                    else return false;
                    break;
                case 4:
                    if (Character.isDigit(c)) estado = 5;
                    else return false;
                    break;
                case 5:
                    if (Character.isDigit(c)) estado = 6;
                    else return false;
                    break;
                case 6:
                    if (Character.isDigit(c)) estado = 7;
                    else return false;
                    break;
                case 7:
                    if (Character.isDigit(c)) estado = 8;
                    else if (c == '.') estado = 18; // Aceita ponto depois do 7º dígito
                    else if (c == '-') estado = 16; // Aceita traço antes do dígito verificador
                    else return false;
                    break;
                case 8:
                    if (Character.isDigit(c)) estado = 9;
                    else if (c == '-') estado = 16; // Aceita traço antes do dígito verificador
                    else return false;
                    break;
                case 9:
                    if (Character.isDigit(c)) estado = 10;
                    else return false;
                    break;
                case 10:
                    if (Character.isDigit(c)) estado = 19; // Estado final para 9 dígitos
                    else if (c == '-') estado = 16; // Aceita traço antes do dígito verificador
                    else return false;
                    break;
                case 16: // Aceita um dígito verificador após o traço
                    if (Character.isDigit(c) || c == 'X') estado = 19; // Aceita 'X' como dígito verificador
                    else return false;
                    break;
                case 18:
                    if (Character.isDigit(c)) estado = 11;
                    else return false;
                    break;
                case 11:
                    if (Character.isDigit(c)) estado = 12;
                    else return false;
                    break;
                case 12:
                    if (c == '.') estado = 13;
                    else return false;
                    break;
                case 13:
                    if (Character.isDigit(c)) estado = 14;
                    else return false;
                    break;
                case 14:
                    if (Character.isDigit(c)) estado = 15;
                    else return false;
                    break;
                case 15:
                    if (Character.isDigit(c)) estado = 19; // Estado final para RG com pontos
                    else return false;
                    break;
                case 19:
                    return false; // Não deve aceitar mais caracteres depois de um RG válido
            }
        }

        return estado == 19; // O RG só é válido se terminar no estado 19
    }
}
