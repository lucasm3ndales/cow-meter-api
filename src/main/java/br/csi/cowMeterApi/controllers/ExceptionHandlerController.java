package br.csi.cowMeterApi.controllers;

import br.csi.cowMeterApi.exceptions.InvalidCpfException;
import br.csi.cowMeterApi.exceptions.InvalidEnumException;
import br.csi.cowMeterApi.exceptions.InvalidRequestDataException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(InvalidCpfException.class)
    public ResponseEntity<String> handleInvalidCpfException(InvalidCpfException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEnumException.class)
    public ResponseEntity<String> handleInvalidEnumException(InvalidEnumException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleInvalidEnumException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestDataException.class)
    public ResponseEntity<String> handleInvalidEnumException(InvalidRequestDataException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
