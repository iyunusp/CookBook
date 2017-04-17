package iyp.cookbook.listing;

import java.io.Serializable;

/**
 * Created by yunus on 04/14/2017.
 */

public class CommentData implements Serializable{
    public String  username,comment;
    public int imageID;
    public float star=0;

    public CommentData(String username, int imageID,String comment, float star) {
        this.imageID=imageID;
        this.username = username;
        this.comment=comment;
        this.star=star;
    }
}
