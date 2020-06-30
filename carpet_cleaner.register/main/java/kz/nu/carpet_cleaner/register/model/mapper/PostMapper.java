package kz.nu.carpet_cleaner.register.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import kz.greetgo.aix_service_bus.register.model.Post;
import org.springframework.jdbc.core.RowMapper;

public class PostMapper implements RowMapper<Post> {

  @Override
  public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
    Post post = new Post();
    post.id = rs.getInt("ID");
    post.postAuthor = rs.getInt("post_author");
    post.postDate = rs.getTimestamp("post_date");
    post.postContent = rs.getString("post_content");
    post.postStatus = rs.getString("post_status");
    post.postTitle = rs.getString("post_title");
    post.postName = rs.getString("post_name");
    post.postType = rs.getString("post_type");
    return post;
  }

}
