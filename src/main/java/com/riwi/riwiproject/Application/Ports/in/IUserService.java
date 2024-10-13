package com.riwi.riwiproject.Application.Ports.in;

import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Request.UserRequestDto;
import com.riwi.riwiproject.Infrastructure.Adapters.In.Rest.Dto.Response.UserResponseDto;
import com.riwi.riwiproject.Infrastructure.Adapters.Out.CRUD.CreateUser;
import com.riwi.riwiproject.Infrastructure.Adapters.Out.CRUD.ReadAll;
import com.riwi.riwiproject.Infrastructure.Adapters.Out.CRUD.Save;
import com.riwi.riwiproject.domain.Model.User;


public interface IUserService extends
                Save<UserResponseDto,
                UserRequestDto>,
                 ReadAll<User>, CreateUser<UserResponseDto,UserRequestDto>
{
}
