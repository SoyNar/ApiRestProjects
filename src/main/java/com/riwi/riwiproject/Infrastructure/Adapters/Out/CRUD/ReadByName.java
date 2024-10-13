package com.riwi.riwiproject.Infrastructure.Adapters.Out.CRUD;

import java.util.List;

public interface ReadByName <EntityDto, usernameAsigned>{
    public List<EntityDto> readByUsernameAsigned(usernameAsigned username);
}
