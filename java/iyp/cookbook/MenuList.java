package iyp.cookbook;


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
import iyp.cookbook.listing.IngredientData;
import iyp.cookbook.listing.MenuData;

public class MenuList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Account account;
    private ImageView banner[]= new ImageView[5];
    private HorizontalScrollView banners;
    private Handler handler= new Handler();
    private Runnable run;
    private boolean status=false;
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
        setContentView(R.layout.activity_menu_list);
        menuchart=(ImageView)findViewById(R.id.menuChart);
        menuchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"your chart are empty", Toast.LENGTH_SHORT).show();
            }
        });
        //TODO database reader
        List<MenuData> data = new ArrayList<>();
        List<IngredientData> ingredients= new ArrayList<>();
        ingredients.add(new IngredientData("tomat",R.drawable.beef_icon,5465));
        ingredients.add(new IngredientData("wortel",R.drawable.lowcarbs,5465));
        ingredients.add(new IngredientData("jagung",R.drawable.soups,5465));
        data.add(new MenuData( "Menu 1", "ini itu adalah menu 1 yang paling enak",R.drawable.belakangprofilepicture,0,ingredients,60,(float)4.5));
        data.add(new MenuData( "Menu 2", "ini itu adalah menu 2 yang paling biasa aja", R.drawable.belakangprofilepicture,0,ingredients,60,(float)3));
        data.add(new MenuData( "Menu 3", "ini itu adalah menu 3 yang paling ga enak", R.drawable.belakangprofilepicture,0,ingredients,60,(float)2.5));
        /*data.add(new Data("", "Image 2"));
        data.add(new Data( "", "Image 3"));
        data.add(new Data( "", "Image 1"));
        data.add(new Data( "", "Image 2"));
        data.add(new Data( "", "Image 3"));
        data.add(new Data( "", "Image 1"));
        data.add(new Data( "", "Image 2"));
        data.add(new Data("", "Image 3"));*/
        this.account=(Account) getIntent().getSerializableExtra("user");
        Toast.makeText(getApplicationContext(), "Welcome "+account.getRealname(), Toast.LENGTH_LONG).show();

        RecyclerView myList = (RecyclerView) findViewById(R.id.recview);
        RecyclerView myList1 = (RecyclerView) findViewById(R.id.newview);
        myList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        myList1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        myList.setAdapter(new MenuAdapter(data,getApplicationContext(),account));
        myList1.setAdapter(new MenuAdapter(data,getApplicationContext(),account));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //banners setting
        banners=(HorizontalScrollView)findViewById(R.id.banner);
        banner[0]=(ImageView)findViewById(R.id.iconBanner1);
        banner[1]=(ImageView)findViewById(R.id.iconBanner2);
        banner[2]=(ImageView)findViewById(R.id.iconBanner3);
        banner[3]=(ImageView)findViewById(R.id.iconBanner4);
        banner[4]=(ImageView)findViewById(R.id.iconBanner5);
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

        /*if (id == R.id.menuBacon) {
            // Handle the camera action
        } else if (id == R.id.menuBeef) {

        } else if (id == R.id.menuChicken) {

        } else if (id == R.id.menuSnack) {

        } else if (id == R.id.menuSeafood) {

        } else if (id == R.id.nav_send || id==R.id.nav_share) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}


