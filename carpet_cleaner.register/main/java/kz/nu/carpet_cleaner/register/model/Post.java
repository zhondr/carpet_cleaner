package kz.nu.carpet_cleaner.register.model;

import java.util.Date;
import lombok.ToString;

@ToString
public class Post {

  public Integer id;
  public Integer postAuthor;
  public Date postDate;
  public String postContent;
  public String postStatus;
  public String postTitle;
  public String postName;
  public String postType;
  public Integer parentPost;
  public String languageCode;

}
