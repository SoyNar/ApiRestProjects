package com.riwi.riwiproject.domain.Excepcions;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    private String message;

}
