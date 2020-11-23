package pl.paweln.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.paweln.jpa.daos.JPAUserDao;
import pl.paweln.jpa.entities.User;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ApplicationJPADao {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationJPADao.class);

    public static void main(String[] args) {

        userDemo();
    }

    private static void userDemo() {
        User user;
        JPAUserDao jpaUserDao = new JPAUserDao();
        jpaUserDao.setEntityManager(JPAUtil.getEntityManager());

        // fetch all
        List<User> userList = jpaUserDao.getAll();
        for (User user1:userList) {
            logger.info("User: ID=" + user1.getFirstName() + " " + user1.getLastName() + " " + user1.getEmailAddress() + " " + user1.getBirthDate());
        }

        logger.info("Fetching data by LastName");
        // fetch all by last Name
        userList = jpaUserDao.findByLastName("Niedziela");
        for (User user1:userList) {
            logger.info("User: ID=" + user1.getFirstName() + " " + user1.getLastName() + " " + user1.getEmailAddress() + " " + user1.getBirthDate());
        }

        // create user
        User newUser = EntitiesBuilder.createUser("Micheal", "Jordan");
        jpaUserDao.save(newUser);

        // clear the persistent context only for testing purposes
        jpaUserDao.clear();

        // retrieve user
        Optional<User> userdb = jpaUserDao.get(newUser.getUserId());
        if (userdb.isPresent()) {
            user = userdb.get();
            logger.info("User: ID=" + user.getFirstName() + " " + user.getLastName() + " " + user.getEmailAddress() + " " + user.getBirthDate());

            // update user

            user.setBirthDate(new Date());
            jpaUserDao.update(user);

            // clear the persistent context only for testing purposes to read record again
            jpaUserDao.clear();

            userdb = jpaUserDao.get(user.getUserId());

            if (userdb.isPresent()) {
                user = userdb.get();
                logger.info("User: ID=" + user.getFirstName() + " " + user.getLastName() + " " + user.getEmailAddress() + " " + user.getBirthDate());

                jpaUserDao.delete(user);
            }



        }



    }
}
