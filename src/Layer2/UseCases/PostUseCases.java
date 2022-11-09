package Layer2.UseCases;

import Layer1.Entity.Post;
import Layer1.Entity.Topic;
import Layer1.Entity.inputboundary.Context;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**<p>This is the post use cases class such that each method will manage one use case of our project, The class will
 * require the maps of the posts and the posts_liked files to initialize and manages the list of posts that it contains
 * {@link Post Post}</p>
 *
 * @Author: eric-qli
 * @Modified by: Yufei Chen, Tianyu Li, Chen Jiang
 */
public class PostUseCases {
    private final Map<Integer, Post> posts;
    private final Map<Integer, ArrayList<Integer>> posts_liked;

    /**<p>This is the constructor for the PostUseCases class, it needs the posts and the posts_liked list </p>
     *
     * @param posts
     * @param posts_liked
     */
    public PostUseCases(Map<Integer, Post> posts, Map<Integer, ArrayList<Integer>> posts_liked){
        this.posts = posts;
        this.posts_liked = posts_liked;
    }

    /**<p>This method is the add post use case, the method receives userId, content, topic of the post and then
     * construct a post object for it.</p>
     *
     * @param postTitle
     * @param userId
     * @param content
     * @param topic
     */
    public void addPost(String postTitle, int userId, String content, String topic){
        Post post = new Post(postTitle,
                userId,
                posts.keySet().size() + 1,
                content,
                LocalDate.now().toString(),
                0,
                0,
                new ArrayList<>(),
                new ArrayList<>(),topic);

        posts.put(post.getId(), post);
    }

    /** <p>This represents the get post use case, the method receives the id the user want to check and returns the
     * corresponding post</p>
     *
     * @param postId
     * @return Post
     */
    public Post getPostFromId(int postId){
        /**
         * pull from the csv file
         */
        return posts.get(postId);
    }

    /**<p>This is the add comment use case, the method will ad the id of the comment to the list comments with in the
     * post object</p>
     *
     * @param postId
     * @param comment_id
     */
    public void addComment_id(int postId, int comment_id){

        posts.get(postId).addListComment(comment_id);
    }

    /**<p>The method will delete the comment id from the post object that is represented by the postId</p>
     *
     * @param postId
     * @param comment_id
     */
    public void deleteComment(int postId, int comment_id){
        /**
         * same as addComment
         */
        posts.get(postId).removeListComment(comment_id);
    }

    public Post searchPost(){
        return null;
    }

    /**<p>This is the like post method, which will leaves a like </p>
     *
     * @param userid
     * @param post_id
     */

    public void like_posts(int userid, int post_id){
        posts.get(post_id).addUserLike(userid);
        posts_liked.get(userid).add(post_id);
    }

    public void unlike_posts(int post_id, int user_id){
        posts.get(post_id).removeUserLike(user_id);
        posts_liked.get(user_id).remove(post_id);
    }

    public String showPost(int post_id){
        getPostFromId(post_id).addViews();
        Post post = getPostFromId(post_id);
        return ("time:" + post.getTime() +"\n"+
                "content:" + post.getContent() + "\n" +
                "id:" + post.getId() + "\n" +
                "sender:" + post.getUserId());
    }

    public boolean changeTitle(int user_id, String title, int post_id){
        if (posts.get(post_id).getUserId() == user_id) {
            posts.get(post_id).setPostTitle(title);
            return true;
        }else{
            return false;
        }
    }

    // dummy method
    public List<Post> getHottestPosts(int post_num) {
        // get post_num amount of the hottest post ranked by popularity.
        List<Post> hotPosts = new ArrayList<>();
        int remaining = post_num;
        for (Map.Entry<Integer, Post> postEntry : this.posts.entrySet()) {
            if (remaining > 0) {
                hotPosts.add(postEntry.getValue());
                remaining = remaining - 1;
            } else {
                return hotPosts;
            }
        }
        return hotPosts;
    }

    public List<Post> getHottestPosts() {
        // Method overload for getHottestPost with default post_num = 3
        return getHottestPosts(3);
    }

    /**
     * questions:
     * 1. how to storage comment
     * 2. delete by comment or commentid
     * 3. delete from the file or hide from the post
     * 4. if this blog is text based then there should be a title for each post
     * 5. search method: search engine? search by post id? should id be private?
     *
     */
}
