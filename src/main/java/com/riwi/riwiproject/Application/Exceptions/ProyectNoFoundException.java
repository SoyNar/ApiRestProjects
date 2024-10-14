package com.riwi.riwiproject.Application.Exceptions;

public class ProyectNoFoundException extends RuntimeException {

        public ProyectNoFoundException(Long id) {
            super("Proyecto con ID " + id + " no fue encontrado.");
        }
}
