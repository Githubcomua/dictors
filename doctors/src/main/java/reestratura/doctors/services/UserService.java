package reestratura.doctors.services;

import reestratura.doctors.models.User;

import java.util.List;

public interface UserService {
    void save(User user);

    List<User> findAll();

    User findOneById(int id);
}
