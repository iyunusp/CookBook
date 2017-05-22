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
        //TODO database reader ASAP
        data= new ArrayList<>();
        //sample filter
        filtered= new ArrayList<>();
        List<IngredientData> ingredients= new ArrayList<>();
        ingredients.add(new IngredientData("tomat",R.drawable.beef_icon,5465));
        ingredients.add(new IngredientData("wortel",R.drawable.lowcarbs,5465));
        ingredients.add(new IngredientData("jagung",R.drawable.soups,5465));
        List<CommentData> com1= new ArrayList<>();
        com1.add(new CommentData("joko",R.mipmap.icon,"bagus",4));
        com1.add(new CommentData("anwar",R.mipmap.icon,"mantap",5));
        com1.add(new CommentData("prabowo",R.mipmap.icon,"gokil",3));

        List<StepData>step2= new ArrayList<>();
        step2.add(new StepData("potong ayam",0));
        step2.add(new StepData("siapkan air mendidih ayam",0));
        step2.add(new StepData("rebus ayam",0));
        step2.add(new StepData("angkat ayam",0));
        step2.add(new StepData("goreng ayam",0));
        step2.add(new StepData("angkat ayam",0));
        data.add(new MenuData( "Menu 1", "ini itu adalah menu 1 yang paling enak","meat",R.drawable.belakangprofilepicture,0,ingredients,60,com1,step2));
        List<CommentData> com2= new ArrayList<>();
        ingredients.add(new IngredientData("onta",R.drawable.soups,222));
        com2.add(new CommentData("prabowo",R.mipmap.icon,"gokil",4));
        com2.add(new CommentData("prabowo",R.mipmap.icon,"gokil",(float)2.5));
        List<StepData>step1= new ArrayList<>();
        step1.add(new StepData("masukkan air",0));
        step1.add(new StepData("rebus air",0));
        step1.add(new StepData("angkat air",0));
        data.add(new MenuData( "Menu 2", "ini itu adalah menu 2 yang paling biasa aja","", R.drawable.belakangprofilepicture,1,ingredients,60,com2,step1));
        List<CommentData> com3= new ArrayList<>();
        ingredients.add(new IngredientData("ssss",R.drawable.soups,3343));
        com3.add(new CommentData("prabowo",R.mipmap.icon,"gokil",(float)0.5));
        data.add(new MenuData( "Menu 3", "ini itu adalah menu 3 yang paling ga enak","meat", R.drawable.belakangprofilepicture,2,ingredients,60,com3,step1));

        myList = (RecyclerView) findViewById(R.id.menuFilter);
        myList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recmen=new MenuAdapterBig(data,this,account);
        myList.setAdapter(recmen);

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
        if(!getIntent().getStringExtra("filter").equals("all")) {
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
            filter="all";
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
