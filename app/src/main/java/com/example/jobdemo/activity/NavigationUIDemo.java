package com.example.jobdemo.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.jobdemo.R;
import com.example.jobdemo.base.BaseActivity;
import com.example.jobdemo.databinding.NavigationuidemoBinding;
import com.example.jobdemo.util.LogUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * BottomNavigationView不好处理红点提示和中间按钮凸出的问题，慎用
 * 要先设置bottomNavigation与NavigationController的关联，再设置setOnNavigationItemSelectedListener
 * 才有效
 */
public class NavigationUIDemo extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "NavigationUIDemo";
    private NavigationuidemoBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = NavigationuidemoBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        navController = Navigation.findNavController(findViewById(R.id.fm_host));
        binding.bnvBar.setItemIconTintList(null);
        NavigationUI.setupWithNavController(binding.bnvBar, navController);
        binding.bnvBar.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
        AppBarConfiguration build = new AppBarConfiguration.Builder(navController.getGraph()).build();
        try {
            NavigationUI.setupWithNavController(getParentToolbar(), navController, build);
        } catch (Exception e) {
            e.printStackTrace();
        }
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                Menu menu = binding.bnvBar.getMenu();
                if (destination.getId() == R.id.fragmentA) {
                    menu.getItem(0).setChecked(true);
                } else if (destination.getId() == R.id.fragmentB) {
                    menu.getItem(1).setChecked(true);
                } else if (destination.getId() == R.id.fragmentC) {
                    menu.getItem(2).setChecked(true);
                }
            }
        });
    }

    /**
     * 底部导航选中时调用,如果menu中itemID和navigate中fragmentID相同，
     * 直接使用NavigationUI.onNavDestinationSelected(item,navController);
     * 不用写那么多if。一定要把fragment和itemID设为一样，这样能省很多事
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        LogUtil.showD(TAG, "itemID==" + item.getItemId());
        LogUtil.showD(TAG, "fragmentAID==" + R.id.fragmentA);
        LogUtil.showD(TAG, "fragmentBID==" + R.id.fragmentB);
        LogUtil.showD(TAG, "fragmentCID==" + R.id.fragmentC);
        if (item.getItemId() == R.id.btn_a_fragment) {
            if (item.isChecked()) {
                LogUtil.showD(TAG, "已经选中了A");
                return true;
            }
            item.setChecked(true);
            navController.navigate(R.id.fragmentA);
        } else if (item.getItemId() == R.id.btn_b_fragment) {
            if (item.isChecked()) {
                LogUtil.showD(TAG, "已经选中了B");
                return true;
            }
            item.setChecked(true);
            navController.navigate(R.id.fragmentB);
        } else if (item.getItemId() == R.id.btn_c_fragment) {
            if (item.isChecked()) {
                LogUtil.showD(TAG, "已经选中了C");
                return true;
            }
            item.setChecked(true);
            navController.navigate(R.id.fragmentC);
        }
        return false;
    }
}
