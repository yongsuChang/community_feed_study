package org.fastcampus.user.application.interfaces;

import java.util.Optional;
import org.fastcampus.user.domain.User;

public interface UserRepository {

    User save(User user);
    Optional<User> findById(Long id);
}
