package org.com.br.Core.Domain.Exceptions;

public class InvalidCPFException extends Exception{

    public InvalidCPFException(String msg){
        super(msg);
    }
    public InvalidCPFException(String msg, Throwable cause){
        super(msg, cause);
    }

}
