package br.csi.cowMeterApi.exceptions;

public class InvalidRequestDataException extends RuntimeException{
    public InvalidRequestDataException(String message) {
        super(message);
    }
}
