package org.com.br.Core.Domain.Rules;

public class ValidaTelefone {

    public static boolean validarTelefone(String telefone) {
        // Expressão regular para o formato "+XX (XX) XXXX-XXXX"
        String regex = "\\+\\d{2} \\(\\d{2}\\) \\d{4}-\\d{4}";

        // Valida se o telefone corresponde ao padrão
        return telefone != null && telefone.matches(regex);
    }

}
