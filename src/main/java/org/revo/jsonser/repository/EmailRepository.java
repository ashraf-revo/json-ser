package org.revo.jsonser.repository;

import org.revo.jsonser.domain.Email;
import org.springframework.data.repository.CrudRepository;

public interface EmailRepository extends CrudRepository<Email, Long> {
}
