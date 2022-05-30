package com.example.socialmedia.Entity;

import javax.persistence.Entity;
import javax.persistence.*;


@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private int id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="user_id")
    private User writer;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="post_id")
    private Post post;

    @Column(name = "content", nullable=false)
    private String content;

    public Comment(User writer, Post post, String content) {
        this.writer = writer;
        this.post = post;
        this.content = content;
    }

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return writer;
    }

    public void setAuthor(User writer) {
        this.writer = writer;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", writer=" + writer +
                ", post=" + post +
                ", content='" + content + '\'' +
                '}';
    }
}
