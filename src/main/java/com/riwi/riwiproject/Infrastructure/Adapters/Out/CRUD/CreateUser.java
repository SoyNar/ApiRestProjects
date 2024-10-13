package com.riwi.riwiproject.Infrastructure.Adapters.Out.CRUD;

public interface CreateUser <EntityResponse,EntityRequest>{
    public EntityResponse createUSerByAdmin(EntityRequest entityRequest);
}
