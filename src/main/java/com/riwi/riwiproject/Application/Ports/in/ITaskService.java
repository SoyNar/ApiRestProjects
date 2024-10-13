package com.riwi.riwiproject.Application.Ports.in;

import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Response.TaksResponseDto;
import com.riwi.riwiproject.Infrastructure.Adapters.Out.CRUD.ReadByName;
import com.riwi.riwiproject.domain.Model.Task;

public interface ITaskService extends ReadByName<TaksResponseDto,String> {
}
