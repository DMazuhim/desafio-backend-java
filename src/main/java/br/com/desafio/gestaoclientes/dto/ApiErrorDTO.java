package br.com.desafio.gestaoclientes.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiErrorDTO {
    
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}