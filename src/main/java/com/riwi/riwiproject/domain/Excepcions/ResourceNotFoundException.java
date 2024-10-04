package com.riwi.techq.domain.Excepcions;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    private String message;

}
