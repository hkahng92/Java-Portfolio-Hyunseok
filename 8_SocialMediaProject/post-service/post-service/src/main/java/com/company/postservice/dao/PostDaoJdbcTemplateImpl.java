package com.company.postservice.dao;

import com.company.postservice.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PostDaoJdbcTemplateImpl implements PostDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PostDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String INSERT_POST_SQL =
            "insert into post (post_date,poster_name,post) values (?, ?, ?)";

    private static final String SELECT_POST_SQL =
            "select * from post where post_id = ?";

    private static final String SELECT_ALL_POST_SQL =
            "select * from post";

    private static final String SELECT_POSTS_BY_POSTER_SQL =
            "select * from post where poster_name = ?";

    private static final String DELETE_POST_SQL =
            "delete from post where post_id = ?";

    private static final String UPDATE_POST_SQL =
            "update post set post_date = ?, poster_name = ?, post = ? where post_id = ?";

    @Override
    @Transactional
    public Post createPost(Post post) {
        jdbcTemplate.update(INSERT_POST_SQL,
                post.getPostDate(),
                post.getPosterName(),
                post.getPost()
                );
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        post.setPostId(id);
        return post;
    }

    @Override
    public Post getPost(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_POST_SQL, this::mapRowToPost, id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Post> getAllPosts(){
        return jdbcTemplate.query(SELECT_ALL_POST_SQL,this::mapRowToPost);
    }

    @Override
    public List<Post> getPostsForPoster(String posterName) {
        return jdbcTemplate.query(SELECT_POSTS_BY_POSTER_SQL,this::mapRowToPost,posterName);
    }

    @Override
    public Post updatePost(Post post) {
        jdbcTemplate.update(
                UPDATE_POST_SQL,
                post.getPostDate(),
                post.getPosterName(),
                post.getPost(),
                post.getPostId()
        );
        return post;
    }

    @Override
    public void deletePost(int id) {
        jdbcTemplate.update(DELETE_POST_SQL,id);
    }
    private Post mapRowToPost (ResultSet rs, int rowNum) throws SQLException {
        Post p = new Post();
        p.setPostId(rs.getInt("post_id"));
        p.setPostDate(rs.getDate("post_date").toLocalDate());
        p.setPosterName(rs.getString("poster_name"));
        p.setPost(rs.getString("post"));
        return p;
    }
}
