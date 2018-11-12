package joana.test.gh_kanban.view;

import android.accounts.Account;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import joana.test.gh_kanban.R;
import joana.test.gh_kanban.view.account.AccountFragment;
import joana.test.gh_kanban.view.explore.ExploreFragment;
import joana.test.gh_kanban.view.local.LocalFragment;
import joana.test.gh_kanban.viewmodel.ExploreViewModel;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationViewListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.nav_explore:
                    loadFragment(ExploreFragment.newInstance(), ExploreFragment.TAG, false);
                    return true;

                case R.id.nav_local:
                    loadFragment(LocalFragment.newInstance(), LocalFragment.TAG, false);
                    return true;
                case R.id.nav_account:
                    loadFragment(AccountFragment.newInstance(), AccountFragment.TAG, false);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment, String tag, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment, tag);
        if (addToBackStack) transaction.addToBackStack(tag);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigation = findViewById(R.id.navigationView);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationViewListener);

        loadFragment(ExploreFragment.newInstance(), ExploreFragment.TAG, false);
    }
}
