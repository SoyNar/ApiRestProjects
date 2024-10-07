package com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Response;

import com.riwi.riwiproject.domain.Model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TaksResponseDto {
    private String userIdAsigned;
    private String title;
    private String description;

}
