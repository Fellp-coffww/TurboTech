package org.com.br.Core.Domain.Exceptions;

public class InvalidTelefoneException extends Exception{

    public InvalidTelefoneException(String msg){
        super(msg);
    }
    public InvalidTelefoneException(String msg, Throwable cause){
        super(msg, cause);
    }

}
