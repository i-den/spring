```java
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToOne(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false,
            orphanRemoval = true // to remove null entities
    )
    @PrimaryKeyJoinColumn
    private PostDetails postDetails;

    // ... getters and setters

    public void setPostDetails(PostDetails postDetails) {
        if (postDetails == null) {
            if (this.postDetails != null) {
                this.postDetails.setPost(null);
            }
        } else {
            postDetails.setPost(this);
        }
        this.postDetails = postDetails;
    }
}
```
Relationship Owner
```java
@Entity(name = "PostDetails")
@Table(name = "post_details")
public class PostDetails {
 
     @Id
     @Column(name = "post_id") // named to bind to post_id
     private Long id;
 
     @Column(name = "created_on")
     private Date createdOn;
 
     @Column(name = "created_by")
     private String createdBy;
 
     @MapsId // Maps the primary key to the post_id
     @OneToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "post_id", unique = true)
     private Post post;
 
     public PostDetails() {}
 
     public PostDetails(String createdBy) {
         createdOn = new Date();
         this.createdBy = createdBy;
     }
}
```

### CREATE
```java
Post post = new Post();
post.setTitle("Post.title");
PostDetails postDetails = new PostDetails("PostDetails.createdBy");
post.setPostDetails(postDetails);

session.save(post);

// Hibernate: insert into Post (title) values (?)
// Hibernate: insert into post_details (created_by, created_on, post_id) values (?, ?, ?)
```

### READ
Two queries as LAZY loaded
```java
public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query<Post> query = session.createQuery("SELECT p FROM Post p LEFT JOIN p.postDetails", Post.class);
        List<Post> posts = query.getResultList();

        System.out.println(posts.get(0).getPostDetails().getCreatedBy());

        session.getTransaction().commit();
        session.close();
    }
}
// Hibernate: select post0_.id as id1_0_, post0_.title as title2_0_ from Post post0_ left outer join post_details postdetail1_ on post0_.id=postdetail1_.post_id
// Hibernate: select postdetail0_.post_id as post_id1_1_0_, postdetail0_.created_by as created_2_1_0_, postdetail0_.created_on as created_3_1_0_ from post_details postdetail0_ where postdetail0_.post_id=?
```

One query with LEFT JOIN FETCH
```java
Query<Post> query = session.createQuery("SELECT p FROM Post p LEFT JOIN FETCH p.postDetails", Post.class);
// Hibernate: select post0_.id as id1_0_0_, postdetail1_.post_id as post_id1_1_1_, post0_.title as title2_0_0_, postdetail1_.created_by as created_2_1_1_, postdetail1_.created_on as created_3_1_1_ from Post post0_ left outer join post_details postdetail1_ on post0_.id=postdetail1_.post_id
```

### UPDATE
```java
Query<Post> query = session.createQuery("SELECT p FROM Post p LEFT JOIN FETCH p.postDetails", Post.class);
Post post = query.getSingleResult();
PostDetails postDetails = post.getPostDetails();
postDetails.setCreatedBy("Update Name");

// Hibernate: select post0_.id as id1_0_0_, postdetail1_.post_id as post_id1_1_1_, post0_.title as title2_0_0_, postdetail1_.created_by as created_2_1_1_, postdetail1_.created_on as created_3_1_1_ from Post post0_ left outer join post_details postdetail1_ on post0_.id=postdetail1_.post_id
// Hibernate: update post_details set created_by=?, created_on=? where post_id=?
```

### DELETE
```java
class Post {
    @OneToOne(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false,
            orphanRemoval = true // <-- HERE
    )
    @PrimaryKeyJoinColumn
    private PostDetails postDetails;
}


Query<Post> query = session.createQuery("SELECT p FROM Post p LEFT JOIN FETCH p.postDetails", Post.class);
Post post = query.getSingleResult();
post.setPostDetails(null);

// Hibernate: select post0_.id as id1_0_0_, postdetail1_.post_id as post_id1_1_1_, post0_.title as title2_0_0_, postdetail1_.created_by as created_2_1_1_, postdetail1_.created_on as created_3_1_1_ from Post post0_ left outer join post_details postdetail1_ on post0_.id=postdetail1_.post_id
// Hibernate: delete from post_details where post_id=?
```



