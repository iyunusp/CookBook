package iyp.cookbook.listing;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yunus on 03/23/2017.
 */

public class MenuData implements Serializable {
    public String Title, Desc;
    public int imageID, recipeID,recipeCategory, minute;
    public float star=0;
    public List<IngredientData> ingredients;
    //TODO Steps
    public MenuData(String Title,String Desc, int imageID,int recipeID,List<IngredientData> ingredients,int minute,float star) {
        this.imageID=imageID;
        this.Desc=Desc;
        this.Title = Title;
        this.recipeID=recipeID;
        this.ingredients=ingredients;
        this.minute=minute;
        this.star=star;
    }
}
