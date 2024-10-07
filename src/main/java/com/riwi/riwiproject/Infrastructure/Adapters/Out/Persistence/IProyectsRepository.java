package com.riwi.riwiproject.Infrastructure.Adapters.Out.Persistence;

import com.riwi.riwiproject.domain.Model.Proyects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProyectsRepository extends JpaRepository<Proyects,Long> {

}
