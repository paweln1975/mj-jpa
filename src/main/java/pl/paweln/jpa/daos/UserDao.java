package pl.paweln.jpa.daos;

import pl.paweln.jpa.entities.User;

import java.util.List;

public interface UserDao extends Dao<User>  {
    List<User> findByLastName(String lastName);
}
