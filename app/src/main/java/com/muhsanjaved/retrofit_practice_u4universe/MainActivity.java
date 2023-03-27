package com.muhsanjaved.retrofit_practice_u4universe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.muhsanjaved.retrofit_practice_u4universe.models.Comment;
import com.muhsanjaved.retrofit_practice_u4universe.models.Post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView output_textView;

    MyWebService myWebService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output_textView = findViewById(R.id.output_text);
        Button btnClear = findViewById(R.id.btnClear);
        Button btnRunCode = findViewById(R.id.btnRunCode);


        btnClear.setOnClickListener(view -> output_textView.setText(""));

        myWebService = MyWebService.RETROFIT.create(MyWebService.class);

        btnRunCode.setOnClickListener(view -> {

            getComments();
           // getPosts();
        });


    }

    private void getComments() {
//        Call<List<Comment>> call = myWebService.getComments(3,"id","desc");
//        Call<List<Comment>> call = myWebService.getComments(null,null,null);

        Map <String,String> parameters = new HashMap<>();
        parameters.put("postId","5");
        parameters.put("_sort","id");
        parameters.put("_order","desc");
//        Call<List<Comment>> call = myWebService.getComments(parameters);

//        Call<List<Comment>> call = myWebService.getComments("https://jsonplaceholder.typicode.com/posts/13/comments");
        Call<List<Comment>> call = myWebService.getComments(new Integer[]{2,4,6,8}, null,null);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful())
                    showComments(response.body());
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
    }

    private void showComments(List<Comment> body) {

        for (Comment comment:body){
            output_textView.append("id: "+comment.getId() +"\n");
            output_textView.append("postId: "+comment.getPostId() +"\n");
            output_textView.append("user: "+comment.getName() +"\n");
            output_textView.append("email: "+comment.getEmail() +"\n\n");
            output_textView.append("body: "+comment.getBody() +"\n\n");
        }
    }

    private void getPosts() {
        Call<List<Post>> call = myWebService.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (response.isSuccessful()){

                    for (Post post :response.body()){
                        showPost(post);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

    private void showPost(Post post) {
        output_textView.append("userId: "+post.getUserId() +"\n");
        output_textView.append("id: "+post.getId() +"\n");
        output_textView.append("title: "+post.getTitle() +"\n");
        output_textView.append("body: "+post.getBody() +"\n\n");
    }
}