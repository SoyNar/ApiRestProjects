package com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProyectResponseDto {
    private String title;
    private String description;
    private List<TaksResponseDto> task;
    private String nameAdmin;
}
