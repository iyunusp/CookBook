package iyp.cookbook.listing;

import java.io.Serializable;

/**
 * Created by yunus on 04/14/2017.
 */

public class IngredientData implements Serializable{
    public String Title;
    public int imageID, IngredientID;
    public boolean clicked=false;

    public IngredientData(String Title, int imageID,int IngredientID) {
        this.imageID=imageID;
        this.Title = Title;
        this.IngredientID=IngredientID;

    }
}
