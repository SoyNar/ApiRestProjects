package com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Response;

import com.riwi.riwiproject.domain.Model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TaksResponseDto {
    private String title;
    private String description;
    private List<User> user;
}
