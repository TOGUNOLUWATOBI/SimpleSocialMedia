package com.example.socialmedia.DAO;

import com.example.socialmedia.Entity.User;
import com.example.socialmedia.Entity.QUser;
import com.example.socialmedia.Utils.HibernateUtil;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import com.querydsl.jpa.impl.JPAQuery;



public class UserDao {


    public static boolean addUser(User user)
    {
        return HibernateUtil.doTransaction(session -> session.save(user));
    }

    public static User getUser(String username) {
        AtomicReference<User> reference = new AtomicReference<>();

        HibernateUtil.doTransaction(session -> {
            JPAQuery<User> query = new JPAQuery<>(session.getEntityManagerFactory().createEntityManager());
            QUser qUser = QUser.user;
            User user = query.select(qUser)
                    .from(qUser)
                    .where(qUser.username.eq(username))
                    .fetchOne();

            reference.set(user);
        });

        return reference.get();
    }

    public static List<User> getAllUsers()
    {
        AtomicReference<List<User>> references=new AtomicReference<>();
        HibernateUtil.doTransaction(session ->
        {
            JPAQuery<User> query=new JPAQuery<>(session.getSessionFactory().createEntityManager());
            QUser qUser=QUser.user;
            List<User> users=query.select(qUser).
                    from(qUser).
                    fetch();
            references.set(users);
        });
        return references.get();
    }

    public static  boolean isUserExist(String username)
    {
        return getUser(username)!=null;
    }


}
