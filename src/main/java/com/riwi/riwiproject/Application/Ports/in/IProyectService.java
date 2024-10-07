package com.riwi.riwiproject.Application.Ports.in;

import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request.ProyectRequesDto;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Response.ProyectResponseDto;
import com.riwi.riwiproject.Infrastructure.Adapters.Out.CRUD.Disable;
import com.riwi.riwiproject.Infrastructure.Adapters.Out.CRUD.Save;
import com.riwi.riwiproject.domain.Model.Proyects;

public interface IProyectService extends
        Save<ProyectResponseDto, ProyectRequesDto>


{
}
