package com.riwi.riwiproject.Infrastructure.Adapters.Out.CRUD;

public interface Save<Entity,EntityDTO>{
    public Entity save(EntityDTO entity);
}
