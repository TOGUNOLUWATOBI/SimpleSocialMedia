package com.example.socialmedia.DAO;


import com.example.socialmedia.Entity.Comment;
import com.example.socialmedia.Entity.Post;
import com.example.socialmedia.Entity.QComment;
import com.example.socialmedia.Utils.HibernateUtil;
import com.querydsl.jpa.impl.JPAQuery;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CommentDao {
    public static boolean addComment (Comment comment)
    {
        return HibernateUtil.doTransaction(session -> session.save(comment));
    }


    public static List<Comment> getAllComments(Post post)
    {
        AtomicReference<List<Comment>> references=new AtomicReference<>();
        HibernateUtil.doTransaction(session ->
        {
            JPAQuery<Comment> query = new JPAQuery<>(session.getSessionFactory().createEntityManager());
            QComment qComment = QComment.comment;
            List<Comment> comments = query.select(qComment).
                    from(qComment).
                    where(qComment.post.id.eq(post.getId())).
                    fetch();
            references.set(comments);
        });
        return  references.get();
    }
}
