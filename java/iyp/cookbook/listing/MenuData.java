package iyp.cookbook.listing;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yunus on 03/23/2017.
 */

public class MenuData implements Serializable {
    public String Title, Desc,tag;
    public int imageID, recipeID, minute;
    public float star=0;
    public List<IngredientData> ingredients;
    public List<CommentData> comments;
    public List<StepData>steps;
    //TODO Steps
    public MenuData(String Title,String Desc,String tag, int imageID,int recipeID,List<IngredientData> ingredients,int minute,List<CommentData> comments,List<StepData> steps) {
        this.imageID=imageID;
        this.Desc=Desc;
        this.Title = Title;
        this.recipeID=recipeID;
        this.ingredients=ingredients;
        this.minute=minute;
        this.comments=comments;
        this.tag=tag;
        this.steps=steps;
        calcStar();
    }
    public void calcStar(){
        float temp=(float)0.0;
        for(CommentData com:comments){
            temp+=com.star;
        }
        this.star=temp/comments.size();
    }
}
