package com.riwi.riwiproject.Infrastructure.Adapters.Out.Persistence;

import com.riwi.riwiproject.domain.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {

}
