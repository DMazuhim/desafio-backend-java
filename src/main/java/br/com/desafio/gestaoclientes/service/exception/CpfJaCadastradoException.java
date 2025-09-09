package br.com.desafio.gestaoclientes.service.exception;

public class CpfJaCadastradoException extends RuntimeException {

    public CpfJaCadastradoException(String message) {
        super(message);
    }
}