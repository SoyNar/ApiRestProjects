package com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TaskUserAsignedRequestDTo {


    private String title;
    private String description;
    private String nameAssigned;
}
