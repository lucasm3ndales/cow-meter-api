package br.csi.cowMeterApi.exceptions;

public class InvalidCpfException extends RuntimeException{
    public InvalidCpfException(String message) {
        super(message);
    }
}
