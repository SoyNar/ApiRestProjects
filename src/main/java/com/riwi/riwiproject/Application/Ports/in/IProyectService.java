package com.riwi.riwiproject.Application.Ports.in;

import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request.ProyectRequesDto;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Response.ProyectResponseDto;
import com.riwi.riwiproject.Infrastructure.Adapters.Out.CRUD.Disable;
import com.riwi.riwiproject.Infrastructure.Adapters.Out.CRUD.ReadAll;
import com.riwi.riwiproject.Infrastructure.Adapters.Out.CRUD.ReadByName;
import com.riwi.riwiproject.Infrastructure.Adapters.Out.CRUD.Save;
import com.riwi.riwiproject.domain.Model.Proyects;
import com.riwi.riwiproject.domain.Model.Task;
import com.riwi.riwiproject.domain.Model.User;

public interface IProyectService extends
        Save<ProyectResponseDto, ProyectRequesDto>, ReadAll<Proyects>,Disable<ProyectResponseDto,Long>


{
}
