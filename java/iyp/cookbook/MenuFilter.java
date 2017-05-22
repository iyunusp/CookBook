package iyp.cookbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import iyp.cookbook.account.Account;
import iyp.cookbook.adapter.MenuAdapterBig;
import iyp.cookbook.listing.CommentData;
import iyp.cookbook.listing.IngredientData;
import iyp.cookbook.listing.MenuData;
import iyp.cookbook.listing.StepData;

/**
 * Created by yunus on 05/05/2017.
 */

public class MenuFilter extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static int pos=0;
    private Account account;
    private List<MenuData> data,filtered;
    private MenuAdapterBig recmen;
    private RecyclerView myList;
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }

    private void init(){
        //TODO database reader ASAP
        data= new ArrayList<>();
        //sample filter
        filtered= new ArrayList<>();
        //menu 1
        List<IngredientData> ingredient1= new ArrayList<>();
        ingredient1.add(new IngredientData("2 cups all-purpose flour",R.drawable.beef_icon,5465));
        ingredient1.add(new IngredientData("1 teaspoon baking soda",R.drawable.lowcarbs,5465));
        ingredient1.add(new IngredientData("1/4 teaspoon salt",R.drawable.soups,5465));
        ingredient1.add(new IngredientData("1/2 cup butter",R.drawable.soups,5465));
        ingredient1.add(new IngredientData("3/4 brown sugar",R.drawable.soups,5465));
        ingredient1.add(new IngredientData("2 eggs beaten",R.drawable.soups,5465));
        ingredient1.add(new IngredientData("2 1/3 cups mashed override bananas",R.drawable.soups,5465));
        List<CommentData> com1= new ArrayList<>();
        com1.add(new CommentData("joko",R.mipmap.icon,"enak",4));
        com1.add(new CommentData("anwar",R.mipmap.icon,"mantap banget resepnya",5));
        com1.add(new CommentData("prabowo",R.mipmap.icon,"agak sedikit asin",3));
        List<StepData>step1= new ArrayList<>();
        step1.add(new StepData("Preheat oven to 350 degrees F (175 degrees C). Lightly grease a 9x5 inch loaf pan.",0));
        step1.add(new StepData("In a large bowl, combine flour, baking soda and salt",0));
        step1.add(new StepData("In a separate bowl, cream together butter and brown sugar.",0));
        step1.add(new StepData("Stir in eggs and mashed bananas until well blended.",0));
        step1.add(new StepData("Stir banana mixture into flour mixture; stir just to moisten.",0));
        step1.add(new StepData("Pour batter into prepared loaf pan.",0));
        step1.add(new StepData("Bake in preheated oven for 60 to 65 minutes, until a toothpick inserted into center of the loaf comes out clean.",0));
        step1.add(new StepData("Let bread cool in pan for 10 minutes, then turn out onto a wire rack.",0));
        data.add(new MenuData( "Banana Banana Bread",
                "Why compromise the banana flavor? This banana bread is moist and delicious with loads of banana flavor! Friends and family love my recipe and say it's by far the best! It's wonderful toasted!! Enjoy! ",
                "baked,low",R.drawable.bananabread,0,ingredient1,110,com1,step1));//bananabread
        //menu 2
        List<IngredientData> ingredient2= new ArrayList<>();
        ingredient2.add(new IngredientData("5 potatoes",R.drawable.lowcarbs,222));
        ingredient2.add(new IngredientData("3 eggs",R.drawable.lowcarbs,222));
        ingredient2.add(new IngredientData("1 cup chopped celery",R.drawable.lowcarbs,222));
        ingredient2.add(new IngredientData("1/2 cup chopped onion",R.drawable.lowcarbs,222));
        ingredient2.add(new IngredientData("1/2 cup sweet pickle relish",R.drawable.lowcarbs,222));
        ingredient2.add(new IngredientData("1/4 teaspoon garlic salt",R.drawable.lowcarbs,222));
        ingredient2.add(new IngredientData("1/4 teaspoon celery salt",R.drawable.lowcarbs,222));
        ingredient2.add(new IngredientData("1 tablespoon prepared mustard",R.drawable.lowcarbs,222));
        ingredient2.add(new IngredientData("ground black pepper to taste",R.drawable.lowcarbs,222));
        ingredient2.add(new IngredientData("1/4 cup mayonnaise",R.drawable.lowcarbs,222));
        List<StepData> step2= new ArrayList<>();
        step2.add(new StepData("Bring a large pot of salted water to a boil.",0));
        step2.add(new StepData("Bring water to a boil; cover, remove from heat, and let eggs stand in hot water for 10 to 12 minutes.",0));
        step2.add(new StepData("Remove from hot water, cool, peel and chop.",0));
        step2.add(new StepData("In a large bowl, combine the potatoes, eggs, celery, onion, relish, garlic salt, celery salt, mustard, pepper and mayonnaise. ",0));
        step2.add(new StepData("Mix together well and refrigerate until chilled.",0));
        List<CommentData> com2= new ArrayList<>();
        com2.add(new CommentData("veganmaster",R.mipmap.icon,"sebagai vegetarian saya suka sekali",5));
        com2.add(new CommentData("awaludin",R.mipmap.icon,"cepet ga repot",5));
        com2.add(new CommentData("lefina",R.mipmap.icon,"biasa aja",2));
        data.add(new MenuData( "Old Fashioned Potato Salad",
                "This is potato salad the old-fashioned way, with eggs, celery and relish. It's really good to serve with chili.",
                "salad,low,quick",R.drawable.potatosalad,0,ingredient2,15,com2,step2));//potatosalad
        //menu3
        List<IngredientData> ingredient3= new ArrayList<>();
        ingredient3.add(new IngredientData("2 skinless, boneless chicken breasts, cut into cubes",R.drawable.lowcarbs,222));
        ingredient3.add(new IngredientData("1/2 teaspoon olive oil",R.drawable.lowcarbs,222));
        ingredient3.add(new IngredientData("1/2 teaspoon minced garlic",R.drawable.lowcarbs,222));
        ingredient3.add(new IngredientData("1/4 teaspoon ground cumin",R.drawable.lowcarbs,222));
        ingredient3.add(new IngredientData("2 (14.5 ounce) cans chicken broth",R.drawable.lowcarbs,222));
        ingredient3.add(new IngredientData("1 cup frozen corn kernels",R.drawable.lowcarbs,222));
        ingredient3.add(new IngredientData("1 cup chopped onion",R.drawable.lowcarbs,222));
        ingredient3.add(new IngredientData("1/2 teaspoon chili powder",R.drawable.lowcarbs,222));
        ingredient3.add(new IngredientData("1 tablespoon lemon juice",R.drawable.lowcarbs,222));
        ingredient3.add(new IngredientData("1 cup chunky salsa",R.drawable.lowcarbs,222));
        ingredient3.add(new IngredientData("8 ounces corn tortilla chips",R.drawable.lowcarbs,222));
        ingredient3.add(new IngredientData("1/2 cup shredded Monterey Jack cheese (optional)",R.drawable.lowcarbs,222));
        List<StepData> step3= new ArrayList<>();
        step3.add(new StepData("In a large pot over medium heat, cook and stir chicken in the oil for 5 minutes.",0));
        step3.add(new StepData("Add the garlic and cumin and mix well.",0));
        step3.add(new StepData("Then add the broth, corn, onion, chili powder, lemon juice, and salsa.",0));
        step3.add(new StepData("Reduce heat to low and simmer for about 20 to 30 minutes.",0));
        step3.add(new StepData("Break up some tortilla chips into individual bowls and pour soup over chips.",0));
        step3.add(new StepData("Top with the Monterey Jack cheese and a little sour cream.",0));
        List<CommentData> com3= new ArrayList<>();
        com3.add(new CommentData("bondan",R.mipmap.icon,"pokoke maknyus",5));
        com3.add(new CommentData("andi",R.mipmap.icon,"gurihnya pas",3));
        com3.add(new CommentData("rosa",R.mipmap.icon,"biasa aja",2));
        com3.add(new CommentData("sayaspam",R.mipmap.icon,"not reccommended",(float)0.5));
        data.add(new MenuData( "Chicken Tortilla Soup V",
                "An easy to make soup that's quite good. Fresh chicken and tortilla chips with vegetables. Makes for a delicious, warm soup. Try garnishing with cheese and/or a little sour cream.",
                "soup,meat",R.drawable.chickensoup,0,ingredient3,70,com3,step3));//chickensoup
        //menu4
        List<IngredientData>ingredient4= new ArrayList<>();
        ingredient4.add(new IngredientData("1 1/2 cups all-purpose flour",R.drawable.lowcarbs,222));
        ingredient4.add(new IngredientData("3 1/2 teaspoons baking powder",R.drawable.lowcarbs,222));
        ingredient4.add(new IngredientData("1 teaspoon salt",R.drawable.lowcarbs,222));
        ingredient4.add(new IngredientData("1 tablespoon white sugar",R.drawable.lowcarbs,222));
        ingredient4.add(new IngredientData("1 1/4 cups milk",R.drawable.lowcarbs,222));
        ingredient4.add(new IngredientData("1 egg",R.drawable.lowcarbs,222));
        ingredient4.add(new IngredientData("3 tablespoons butter, melted",R.drawable.lowcarbs,222));
        List<StepData> step4= new ArrayList<>();
        step4.add(new StepData("In a large bowl, sift together the flour, baking powder, salt and sugar.",0));
        step4.add(new StepData("Make a well in the center and pour in the milk, egg and melted butter; mix until smooth.",0));
        step4.add(new StepData("Heat a lightly oiled griddle or frying pan over medium high heat.",0));
        step4.add(new StepData("Pour or scoop the batter onto the griddle, using approximately 1/4 cup for each pancake.",0));
        step4.add(new StepData("Brown on both sides and serve hot.",0));
        List<CommentData> com4= new ArrayList<>();
        com4.add(new CommentData("fahmi",R.mipmap.icon,"simple AF",5));
        data.add(new MenuData( "Good Old Fashioned Pancakes",
                "This is a great recipe that I found in my Grandma's recipe book. Judging from the weathered look of this recipe card, this was a family favorite.",
                "dessert,quick,low",R.drawable.pancakes,0,ingredient4,20,com4,step4));//pancakes
        //menu5
        List<IngredientData> ingredient5= new ArrayList<>();
        ingredient5.add(new IngredientData("8 ounces uncooked elbow macaroni",R.drawable.lowcarbs,222));
        ingredient5.add(new IngredientData("2 cups shredded sharp Cheddar cheese",R.drawable.lowcarbs,222));
        ingredient5.add(new IngredientData("1/2 cup grated Parmesan cheese",R.drawable.lowcarbs,222));
        ingredient5.add(new IngredientData("3 cups milk",R.drawable.lowcarbs,222));
        ingredient5.add(new IngredientData("1/4 cup butter",R.drawable.lowcarbs,222));
        ingredient5.add(new IngredientData("2 1/2 tablespoons all-purpose flour",R.drawable.lowcarbs,222));
        ingredient5.add(new IngredientData("2 tablespoons butter",R.drawable.lowcarbs,222));
        ingredient5.add(new IngredientData("1/2 cup bread crumbs",R.drawable.lowcarbs,222));
        ingredient5.add(new IngredientData("1 pinch paprika",R.drawable.lowcarbs,222));
        List<StepData>step5= new ArrayList<>();
        step5.add(new StepData("Cook macaroni according to the package directions. Drain.",0));
        step5.add(new StepData("In a saucepan, melt butter or margarine over medium heat. Stir in enough flour to make a roux. Add milk to roux slowly, stirring constantly.",0));
        step5.add(new StepData("Stir in cheeses, and cook over low heat until cheese is melted and the sauce is a little thick.",0));
        step5.add(new StepData("Put macaroni in large casserole dish, and pour sauce over macaroni. Stir well.",0));
        step5.add(new StepData("Melt butter or margarine in a skillet over medium heat.",0));
        step5.add(new StepData("Add breadcrumbs and brown.",0));
        step5.add(new StepData("Spread over the macaroni and cheese to cover. Sprinkle with a little paprika.",0));
        step5.add(new StepData("Bake at 350 degrees F (175 degrees C) for 30 minutes. Serve.",0));
        List<CommentData>com5= new ArrayList<>();
        com5.add(new CommentData("fajar",R.mipmap.icon,"kejunya mantap",4));
        com5.add(new CommentData("geger",R.mipmap.icon,"pengen bikin melulu",(float)3.5));
        data.add(new MenuData( "Homemade Mac and Cheese",
                "This is a nice rich mac and cheese. Serve with a salad for a great meatless dinner. Hope you enjoy it.",
                "dessert",R.drawable.cheesemac,0,ingredient5,50,com5,step5));//cheesemac
        //menu6
        List<IngredientData> ingredient6= new ArrayList<>();
        ingredient6.add(new IngredientData("3 cups all-purpose flour",R.drawable.lowcarbs,222));
        ingredient6.add(new IngredientData("1 1/2 tablespoons garlic salt",R.drawable.lowcarbs,222));
        ingredient6.add(new IngredientData("1 tablespoon ground black pepper",R.drawable.lowcarbs,222));
        ingredient6.add(new IngredientData("1 tablespoon paprika",R.drawable.lowcarbs,222));
        ingredient6.add(new IngredientData("1/2 teaspoon poultry seasoning",R.drawable.lowcarbs,222));
        ingredient6.add(new IngredientData("1 1/3 cups all-purpose flour",R.drawable.lowcarbs,222));
        ingredient6.add(new IngredientData("1 teaspoon salt",R.drawable.lowcarbs,222));
        ingredient6.add(new IngredientData("1/4 teaspoon ground black pepper",R.drawable.lowcarbs,222));
        ingredient6.add(new IngredientData("2 egg yolks, beaten",R.drawable.lowcarbs,222));
        ingredient6.add(new IngredientData("1 1/2 cups beer or water",R.drawable.lowcarbs,222));
        ingredient6.add(new IngredientData("1 quart vegetable oil for frying",R.drawable.lowcarbs,222));
        ingredient6.add(new IngredientData("1 (3 pound) whole chicken, cut into pieces",R.drawable.lowcarbs,222));
        List<StepData>step6= new ArrayList<>();
        step6.add(new StepData("In one medium bowl, mix together 3 cups of flour, garlic salt, 1 tablespoon black pepper, paprika and poultry seasoning.",0));
        step6.add(new StepData("In a separate bowl, stir together 1 1/3 cups flour, salt, 1/4 teaspoon pepper, egg yolks and beer.",0));
        step6.add(new StepData("Heat the oil in a deep-fryer to 350 degrees F (175 degrees C).",0));
        step6.add(new StepData("Moisten each piece of chicken with a little water, then dip in the dry mix. ",0));
        step6.add(new StepData("Shake off excess and dip in the wet mix, then dip in the dry mix once more.",0));
        step6.add(new StepData("Carefully place the chicken pieces in the hot oil. Fry for 15 to 18 minutes, or until well browned.",0));
        step6.add(new StepData("Remove and drain on paper towels before serving.",0));
        data.add(new MenuData( "Triple Dipped Fried Chicken",
                "This is the crispiest, spiciest, homemade fried chicken I have ever tasted! It is equally good served hot or cold and has been a picnic favorite in my family for years.",
                "fried,meat",R.drawable.triplefried,0,ingredient6,50,com1,step6));//triplefried
        myList = (RecyclerView) findViewById(R.id.menuFilter);
        myList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recmen=new MenuAdapterBig(data,this,account);
        myList.setAdapter(recmen);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView profilUname,profilMail;
        ImageView profilImage,menuchart;

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu_filter);
        menuchart=(ImageView)findViewById(R.id.menuChart);
        menuchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"your chart are empty", Toast.LENGTH_SHORT).show();
            }
        });
        this.account=(Account) getIntent().getSerializableExtra("user");
        init();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);//remove hamburger icon
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        //how to edit text in nav list header
        View header=navigationView.getHeaderView(0);
        profilUname=(TextView)header.findViewById(R.id.UnameTxt);
        profilMail=(TextView)header.findViewById(R.id.Mailtxt);
        profilImage=(ImageView)header.findViewById(R.id.Profilview) ;
        profilUname.setText(this.account.getRealname());
        profilMail.setText(this.account.getMail());
        profilImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"You're Nice", Toast.LENGTH_SHORT).show();
            }
        });
        //filtering
        if(!getIntent().getStringExtra("filter").equals("")) {
            changeFillter(getIntent().getStringExtra("filter"));
        }
    }
    private void changeFillter(String filter){
        if(filter.equals("")){
            recmen= new MenuAdapterBig(data,this,account);
            myList.setAdapter(recmen);
        }else {
            filtered.removeAll(filtered);
            for( MenuData menu:data){
                if(menu.tag.contains(filter)){
                    filtered.add(menu);
                }
            }
            recmen = new MenuAdapterBig(filtered, this, account);
            myList.setAdapter(recmen);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2){
            if(resultCode==RESULT_OK) {
                MenuData update = (MenuData) data.getSerializableExtra("update");
                this.data.set(pos, update);
                recmen.notifyDataSetChanged();
            }
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String filter="";
        if (id == R.id.menuAll) {
            // Handle the camera action
        } else if (id == R.id.menuMeat) {
            filter="meat";
        } else if (id == R.id.menuSalad) {
            filter="salad";
        } else if (id == R.id.menuDessert) {
            filter="dessert";
        } else if (id == R.id.menuQuick) {
            filter="quick";
        }else if (id == R.id.menuLow) {
            filter="low";
        }else if (id == R.id.menuFried) {
            filter="fried";
        }else if (id == R.id.menuBaked) {
            filter="baked";
        }else if (id == R.id.menuSoup) {
            filter="soup";
        } else  {

        }
        changeFillter(filter);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
