package com.riwi.riwiproject.Infrastructure.Adapters.Out.CRUD;

public interface Save<EntityResponse,EntityRequest>{
    public EntityResponse save(EntityRequest request);
}
