package com.riwi.riwiproject.Application.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponses {


    private String message;
    private int statusCode;
    private LocalDateTime timestamp;
    private String details;
}
