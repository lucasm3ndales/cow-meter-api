package br.csi.cowMeterApi.exceptions;

public class InvalidEnumException extends RuntimeException{
    public InvalidEnumException(String message) {
        super(message);
    }
}
