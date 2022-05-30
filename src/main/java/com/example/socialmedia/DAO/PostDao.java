package com.example.socialmedia.DAO;

import com.example.socialmedia.Entity.Post;
import com.example.socialmedia.Entity.QPost;
import com.example.socialmedia.Utils.HibernateUtil;
import com.querydsl.jpa.impl.JPAQuery;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class PostDao {
    public static Post getPost(int id)
    {
        AtomicReference<Post> reference = new AtomicReference<>();
        HibernateUtil.doTransaction(session ->
        {
            JPAQuery<Post> query = new JPAQuery<>(session.getSessionFactory().createEntityManager());
            QPost qPost = QPost.post;
            Post post=query.select(qPost).
                    from(qPost).
                    where(qPost.id.eq(id)).
                    fetchOne();
            reference.set(post);
        });
        return reference.get();
    }

    public static Post getPost(String title)
    {
        AtomicReference<Post> reference = new AtomicReference<>();
        HibernateUtil.doTransaction(session ->
        {
            JPAQuery<Post> query = new JPAQuery<>(session.getSessionFactory().createEntityManager());
            QPost qPost = QPost.post;
            Post post=query.select(qPost).
                    from(qPost).
                    where(qPost.title.eq(title)).
                    fetchOne();
            reference.set(post);
        });
        return reference.get();
    }

    //Add a user's post
    public static boolean addPost(Post post)
    {
        return HibernateUtil.doTransaction(session -> session.save(post));
    }

    //Get all post in database
    public static List<Post> getAllPosts()
    {
        AtomicReference<List<Post>> references=new AtomicReference<>();
        HibernateUtil.doTransaction(session ->
        {
            JPAQuery<Post> query=new JPAQuery<>(session.getSessionFactory().createEntityManager());
            QPost qPost=QPost.post;
            List<Post> posts=query.select(qPost).
                    from(qPost).
                    fetch();
            references.set(posts);
        });
        return references.get();
    }



}
