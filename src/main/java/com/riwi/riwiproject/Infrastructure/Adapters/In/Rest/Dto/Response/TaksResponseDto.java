package com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Response;

import com.riwi.riwiproject.domain.Model.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaksResponseDto {
    private String title;
    private String description;
    private String user;
}
