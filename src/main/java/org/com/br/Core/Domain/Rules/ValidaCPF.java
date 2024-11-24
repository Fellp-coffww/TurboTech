package org.com.br.Core.Domain.Rules;

public class ValidaCPF {

    public static boolean isCPFValid(String cpf) {
        // Remove caracteres não numéricos

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais (ex: 111.111.111-11)
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            // Cálculo do primeiro dígito verificador
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }
            int firstVerifier = sum % 11;
            firstVerifier = (firstVerifier < 2) ? 0 : 11 - firstVerifier;

            // Verifica o primeiro dígito
            if (firstVerifier != Character.getNumericValue(cpf.charAt(9))) {
                return false;
            }

            // Cálculo do segundo dígito verificador
            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }
            int secondVerifier = sum % 11;
            secondVerifier = (secondVerifier < 2) ? 0 : 11 - secondVerifier;

            // Verifica o segundo dígito
            return secondVerifier == Character.getNumericValue(cpf.charAt(10));

        } catch (NumberFormatException e) {
            return false; // Retorna falso caso ocorra alguma inconsistência
        }
    }

}
