package org.revo.jsonser.repository;

import org.revo.jsonser.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
