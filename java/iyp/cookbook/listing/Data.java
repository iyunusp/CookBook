package iyp.cookbook.listing;

/**
 * Created by yunus on 03/23/2017.
 */

public class Data {
    public String Title;
    public int imageID, recipeID,recipeCategory;

    public Data(String Title, int imageID,int recipeID) {
        this.imageID=imageID;
        this.Title = Title;
        this.recipeID=recipeID;

    }
}
