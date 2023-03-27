package com.muhsanjaved.retrofit_practice_u4universe;

import com.muhsanjaved.retrofit_practice_u4universe.models.Comment;
import com.muhsanjaved.retrofit_practice_u4universe.models.Post;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface MyWebService {

    String BASE_URI = "https://jsonplaceholder.typicode.com/";
    String FEED = "posts";

    Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(BASE_URI)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    // get List of Posts
    @GET(FEED)
    Call<List<Post>> getPosts();

    // dynamic url parameters with path
//    @GET("posts/{id}/comments")
//    Call<List<Comment>> getComments(@Path("id") int userId);

    // query parameters
    @GET("comments")
    Call<List<Comment>> getComments(@QueryMap Map<String ,String> params);

    // query map
    @GET("comments")
    Call<List<Comment>> getComments(@Query("postId") Integer[] ids,//@Query("postId") Integer postId,//int postId,
                                    @Query("_sort") String sortBy,
                                    @Query("_order") String orderBy
                                    );

    @GET("comments")
    Call<List<Comment>> getComments(@Url String url);
}
