package Layer3.Controller;

import Layer1.Entity.Comment;
import Layer1.Entity.Post;
import Layer2.UseCases.CommentUseCases;
import Layer2.UseCases.PostUseCases;
import Layer4.Interface.csvInterface;

import java.util.ArrayList;
import java.util.Map;

/**
 * <p>This class contains the methods for showPostScreen and uploadPostScreen</p>
 *
 * @author Kevin Wu
 */
public class PostController {
    /**
     * <p>Add a post object from GUI's user input to csv file</p>
     *
     * @param title the title of the post
     * @param content the content of the post
     * @param userid the id of the user
     * @param topic the topic of the post
     */
    public void addPost(String title, String content, int userid, String topic) throws Exception {
        csvInterface csvInterface = new csvInterface();
        Map<Integer, Post> posts = csvInterface.postsReader("database/posts.csv");
        Map<Integer, ArrayList<Integer>> postsLiked = csvInterface.postsLikedReader("database/posts_liked.csv");
        PostUseCases postManager = new PostUseCases(posts, postsLiked);
        postManager.addPost(title, userid, content, topic);
        csvInterface.postsWriter("database/posts.csv", posts);
        csvInterface.postsLikedWriter("database/posts_liked.csv", postsLiked);
    }

    /**
     * <p>Get the post object from csv file and pass it to GUI to present the post</p>
     *
     * @param postid the id of the post
     * @return the post object
     */
    public String showPost(int postid) throws Exception {
        csvInterface csvInterface = new csvInterface();
        Map<Integer, Post> posts = csvInterface.postsReader("database/posts.csv");
        Map<Integer, ArrayList<Integer>> postsLiked = csvInterface.postsLikedReader("database/posts_liked.csv");
        PostUseCases postManager = new PostUseCases(posts, postsLiked);
        String post = postManager.showPost(postid);
        return post;
    }
}
