package iyp.cookbook;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import iyp.cookbook.account.Account;
import iyp.cookbook.fragment.MenuCommunityFragment;
import iyp.cookbook.fragment.MenuIngredientsFragment;
import iyp.cookbook.fragment.MenuOverviewFragment;
import iyp.cookbook.fragment.MenuStepsFragment;
import iyp.cookbook.listing.MenuData;

public class RecipeItemView extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        MenuOverviewFragment.OnFragmentInteractionListener,
        MenuIngredientsFragment.OnFragmentInteractionListener,
        MenuStepsFragment.OnFragmentInteractionListener,
        MenuCommunityFragment.OnFragmentInteractionListener{

    private Account account;
    private ViewPager viewpager;
    private SectionsPagerAdapter section;
    private MenuData menu;
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_item_view);
        TextView profilUname, profilMail;
        ImageView profilImage, menuchart;

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_recipe_item_view);
        menuchart = (ImageView) findViewById(R.id.menuChart);
        menuchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "your chart are empty", Toast.LENGTH_SHORT).show();
            }
        });
        //passing user detail
        this.account = (Account) getIntent().getSerializableExtra("user");
        this.menu=(MenuData) getIntent().getSerializableExtra("menu");
        //
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //side nav setting
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);//remove hamburger icon
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //tabbed TODO fragment help
        section= new SectionsPagerAdapter(getSupportFragmentManager());
        viewpager = (ViewPager) findViewById(R.id.menuHome);
        viewpager.setAdapter(section);

        //clickable text view
        TextView overview=(TextView)findViewById(R.id.textOverview);
        TextView ingredients=(TextView)findViewById(R.id.textIngredients);
        TextView steps=(TextView)findViewById(R.id.textSteps);
        TextView community=(TextView)findViewById(R.id.textCommunity);
        overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewpager.setCurrentItem(0);
            }
        });
        ingredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewpager.setCurrentItem(1);
            }
        });
        steps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewpager.setCurrentItem(2);
            }
        });
        community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewpager.setCurrentItem(3);
            }
        });
        //side nav
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        //how to edit text in nav list header
        View header = navigationView.getHeaderView(0);
        profilUname = (TextView) header.findViewById(R.id.UnameTxt);
        profilMail = (TextView) header.findViewById(R.id.Mailtxt);
        profilImage = (ImageView) header.findViewById(R.id.Profilview);
        profilUname.setText(this.account.getRealname());
        profilMail.setText(this.account.getMail());
        profilImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "You're Nice", Toast.LENGTH_SHORT).show();
            }
        });
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 1: return MenuIngredientsFragment.newInstance(menu.imageID,menu.ingredients,viewpager);
                case 2: return MenuStepsFragment.newInstance();
                case 3: return MenuCommunityFragment.newInstance();
                default: return MenuOverviewFragment.newInstance(menu.Title,menu.Desc,menu.imageID,menu.minute,menu.star,viewpager);//default is home screen
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

}
