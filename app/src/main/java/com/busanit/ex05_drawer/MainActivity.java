package com.busanit.ex05_drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentCallBack {

    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar
                ,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.menu1){
            Toast.makeText(this,"첫번째 메뉴 선택됨",Toast.LENGTH_SHORT).show();
            onFragmentSelected(0,null);
        } else if(id==R.id.menu2){
            Toast.makeText(this,"두번째 메뉴 선택됨",Toast.LENGTH_SHORT).show();
            onFragmentSelected(1,null);
        } else if(id==R.id.menu3){
            Toast.makeText(this,"세번째 메뉴 선택됨",Toast.LENGTH_SHORT).show();
            onFragmentSelected(2,null);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentSelected(int position, Bundle bundle) {
        Fragment curFragment = null;
        if(position==0){
            curFragment=fragment1;
            toolbar.setTitle("통화기록");
        } else if(position==1){
            curFragment=fragment2;
            toolbar.setTitle("스팸기록");
        } else if(position==2){
            curFragment=fragment3;
            toolbar.setTitle("연락처");
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, curFragment).commit();
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }

    }
}