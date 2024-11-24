package org.com.br.Core.Domain.Rules;

public class ValidaCNPJ {

    public static boolean isValidCnpj(String cnpj) {
        // Remove caracteres especiais como pontos e barras
        cnpj = cnpj.replaceAll("\\D", "");

        // Verifica se tem 14 dígitos
        if (cnpj.length() != 14) {
            return false;
        }

        // Verifica se todos os dígitos são iguais, o que invalida o CNPJ
        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        try {
            // Calcula o primeiro dígito verificador
            int[] pesosPrimeiro = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int soma = 0;
            for (int i = 0; i < 12; i++) {
                soma += Character.getNumericValue(cnpj.charAt(i)) * pesosPrimeiro[i];
            }
            int primeiroDigito = 11 - (soma % 11);
            primeiroDigito = (primeiroDigito >= 10) ? 0 : primeiroDigito;

            // Calcula o segundo dígito verificador
            int[] pesosSegundo = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            soma = 0;
            for (int i = 0; i < 13; i++) {
                soma += Character.getNumericValue(cnpj.charAt(i)) * pesosSegundo[i];
            }
            int segundoDigito = 11 - (soma % 11);
            segundoDigito = (segundoDigito >= 10) ? 0 : segundoDigito;

            // Verifica se os dígitos calculados são iguais aos fornecidos
            return primeiroDigito == Character.getNumericValue(cnpj.charAt(12))
                    && segundoDigito == Character.getNumericValue(cnpj.charAt(13));

        } catch (NumberFormatException e) {
            return false; // Caso haja erro ao interpretar os caracteres como números
        }
    }

}
