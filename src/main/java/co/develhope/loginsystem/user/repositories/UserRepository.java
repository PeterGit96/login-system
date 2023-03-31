package co.develhope.loginsystem.user.repositories;

import co.develhope.loginsystem.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByActivationCode(String activationCode);
    User findByPasswordResetCode(String passwordResetCode);

}
