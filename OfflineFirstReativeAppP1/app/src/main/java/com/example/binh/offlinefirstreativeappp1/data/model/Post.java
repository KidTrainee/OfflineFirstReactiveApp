package com.example.binh.offlinefirstreativeappp1.data.model;


import com.example.binh.offlinefirstreativeappp1.data.local.DatabaseContract;
import com.pushtorefresh.storio.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio.contentresolver.annotations.StorIOContentResolverType;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;

@StorIOSQLiteType(table = DatabaseContract.PostTableContract.TABLE_NAME)
@StorIOContentResolverType(uri = DatabaseContract.PostTableContract.CONTENT_URI_STRING)
public class Post {
    @StorIOSQLiteColumn(name = DatabaseContract.PostTableContract.COLUMN_ID, key = true)
    @StorIOContentResolverColumn(name = DatabaseContract.PostTableContract.COLUMN_ID, key = true)
    public Integer id;

    @StorIOSQLiteColumn(name = DatabaseContract.PostTableContract.COLUMN_USER_ID)
    @StorIOContentResolverColumn(name = DatabaseContract.PostTableContract.COLUMN_USER_ID)
    public  Integer userId;

    @StorIOSQLiteColumn(name = DatabaseContract.PostTableContract.COLUMN_TITLE)
    @StorIOContentResolverColumn(name = DatabaseContract.PostTableContract.COLUMN_TITLE)
    public  String title;

    @StorIOSQLiteColumn(name = DatabaseContract.PostTableContract.COLUMN_BODY)
    @StorIOContentResolverColumn(name = DatabaseContract.PostTableContract.COLUMN_BODY)
    public  String body;

    public Post(Integer id, Integer userId, String title, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public Post() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
