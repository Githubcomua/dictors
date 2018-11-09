package reestratura.doctors.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import reestratura.doctors.models.User;

public interface UserDAO extends JpaRepository<User, Integer> {
 UserDetails findByUsername(String username);

}
