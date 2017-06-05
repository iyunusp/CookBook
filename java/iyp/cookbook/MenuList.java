package iyp.cookbook;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import iyp.cookbook.account.Account;
import iyp.cookbook.adapter.MenuAdapter;
import iyp.cookbook.listing.CommentData;
import iyp.cookbook.listing.IngredientData;
import iyp.cookbook.listing.MenuData;
import iyp.cookbook.listing.StepData;

public class MenuList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static int pos=0;
    private Account account;
    private ImageView banner[]= new ImageView[5];
    private HorizontalScrollView banners;
    private Handler handler= new Handler();
    private Runnable run;
    private List<MenuData> recs= new ArrayList<>(),news= new ArrayList<>();
    private MenuAdapter recmen,newmen;
    private boolean status=false;
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }

    private void init(){
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
        recs.add(new MenuData( "Banana Banana Bread",
                "Why compromise the banana flavor? This banana bread is moist and delicious with loads of banana flavor! Friends and family love my recipe and say it's by far the best! It's wonderful toasted!! Enjoy! ",
                "baked,low",R.drawable.bananabread,0,ingredient1,110,com1,step1));//bananabread
        news.add(new MenuData( "Banana Banana Bread",
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
        recs.add(new MenuData( "Old Fashioned Potato Salad",
                "This is potato salad the old-fashioned way, with eggs, celery and relish. It's really good to serve with chili.",
                "salad,low,quick",R.drawable.potatosalad,0,ingredient2,15,com2,step2));//potatosalad
        //menu4
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
        news.add(new MenuData( "Chicken Tortilla Soup V",
                "An easy to make soup that's quite good. Fresh chicken and tortilla chips with vegetables. Makes for a delicious, warm soup. Try garnishing with cheese and/or a little sour cream.",
                "soup,meat",R.drawable.chickensoup,0,ingredient3,70,com3,step3));//chickensoup
        recs.add(new MenuData( "Chicken Tortilla Soup V",
                "An easy to make soup that's quite good. Fresh chicken and tortilla chips with vegetables. Makes for a delicious, warm soup. Try garnishing with cheese and/or a little sour cream.",
                "soup,meat",R.drawable.chickensoup,0,ingredient3,70,com3,step3));//chickensoup
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
        news.add(new MenuData( "Triple Dipped Fried Chicken",
                "This is the crispiest, spiciest, homemade fried chicken I have ever tasted! It is equally good served hot or cold and has been a picnic favorite in my family for years.",
                "fried,meat",R.drawable.triplefried,0,ingredient6,50,com1,step6));//triplefried
        RecyclerView myList = (RecyclerView) findViewById(R.id.recview);
        RecyclerView myList1 = (RecyclerView) findViewById(R.id.newview);
        myList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        myList1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recmen=new MenuAdapter(recs,this,account);
        newmen=new MenuAdapter(news,this,account);
        myList.setAdapter(recmen);
        myList1.setAdapter(newmen);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView profilUname,profilMail;
        ImageView profilImage,menuchart;

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu_list);
        menuchart=(ImageView)findViewById(R.id.menuChart);
        menuchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"your Bookmark are empty", Toast.LENGTH_SHORT).show();
            }
        });
        //TODO database reader ASAP
        this.account=(Account) getIntent().getSerializableExtra("user");
        Toast.makeText(getApplicationContext(), "Welcome "+account.getRealname(), Toast.LENGTH_LONG).show();
        init();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //banners setting
        banners=(HorizontalScrollView)findViewById(R.id.banner);
        banner[0]=(ImageView)findViewById(R.id.iconBanner1);
        banner[0].setImageResource(R.drawable.diskonmahasiswa);
        banner[1]=(ImageView)findViewById(R.id.iconBanner2);
        banner[1].setImageResource(R.drawable.oktoberevent);
        banner[2]=(ImageView)findViewById(R.id.iconBanner3);
        banner[2].setImageResource(R.drawable.diskonmahasiswa);
        banner[3]=(ImageView)findViewById(R.id.iconBanner4);
        banner[3].setImageResource(R.drawable.oktoberevent);
        banner[4]=(ImageView)findViewById(R.id.iconBanner5);
        banner[4].setImageResource(R.drawable.diskonmahasiswa);
        final DisplayMetrics metric=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        for(ImageView ban:banner){
            ban.getLayoutParams().width=metric.widthPixels-(8*(int)metric.density);
        }
        run= new Runnable() {
            int i=0;
            @Override
            public void run() {
                status=true;
                if(i>4) {//reach max
                    banners.smoothScrollTo(0, 0);
                    i=0;
                }
                banners.smoothScrollTo((metric.widthPixels*i),0);//8 == margin
                i++;
                handler.postDelayed(run,5000);
            }
        };


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
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2){
            if(resultCode==RESULT_OK) {
                if(data.getBooleanExtra("HOME",true)){
                    return;
                }
                MenuData update = (MenuData) data.getSerializableExtra("update");
                if (update.Title.equals(recs.get(pos).Title)) {
                    this.recs.set(pos, update);
                    recmen.notifyDataSetChanged();
                }
                if (update.Title.equals(news.get(pos).Title)) {
                    this.news.set(pos, update);
                    newmen.notifyDataSetChanged();
                }
            }
        }
    }
    @Override
    public void onPause(){
        handler.removeCallbacks(run);
        status=false;
        super.onPause();
    }
    @Override
    public void onResume(){
        if(!status) {
            handler.postDelayed(run, 5000);
        }
        super.onResume();
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
        Intent fill= new Intent(getApplicationContext(),MenuFilter.class);
        fill.putExtra("user",account);
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
        }else  {
            filter="n";
            return true;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        if(!filter.equals("n")) {
            fill.putExtra("filter", filter);
            getApplicationContext().startActivity(fill);
        }
        return true;
    }


}


