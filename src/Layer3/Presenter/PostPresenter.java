package Layer3.Presenter;
import Layer2.UseCases.PostUseCases;
import Layer4.Interface.csvInterface;
import Model.Request.PostRequestModel;
import Model.Response.PostResponseModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class PostPresenter {

    Map<Integer, PostRequestModel> posts;
    Map<Integer, ArrayList<Integer>> postLiked;
    PostUseCases postManager;

    public PostPresenter() throws IOException {
        csvInterface csvInterface = new csvInterface();
        posts = csvInterface.postsReader("database/post.csv");
        postLiked = csvInterface.postsLikedReader("database/post_liked.csv");
        postManager = new PostUseCases(posts, postLiked);
    }

    public ArrayList<Object> showPost(int postId) {
        return postManager.getDetails(postId);
    }

    public Map<Integer, PostResponseModel> getPosts(){return postManager.getPostsResponseModel();}
}
