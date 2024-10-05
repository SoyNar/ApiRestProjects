package com.riwi.riwiproject.Infrastructure.Adapters.Out.CRUD;

public interface Disable <Entity, ID>{
    public Entity update(ID id, Entity entity);
}
