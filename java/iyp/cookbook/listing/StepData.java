package iyp.cookbook.listing;

import java.io.Serializable;

/**
 * Created by yunus on 05/22/2017.
 */

public class StepData implements Serializable{
    public String desc;
    public int img;
    public StepData(String desc, int img){
        this.desc=desc;
        this.img=img;
    }
}
