package org.com.br.Core.Domain.Exceptions;

public class InvalidCPNJException extends Exception{

    public InvalidCPNJException(String msg){
        super(msg);
    }
    public InvalidCPNJException(String msg, Throwable cause){
        super(msg, cause);
    }


}
