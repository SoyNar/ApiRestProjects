package com.riwi.riwiproject.Infrastructure.Adapters.Out.CRUD;

public interface ReadByID<EntityDto, ID> {
    public EntityDto readById(ID id);
}
