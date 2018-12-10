package joana.test.gh_kanban.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import joana.test.gh_kanban.R;
import joana.test.gh_kanban.model.local.Board;
import joana.test.gh_kanban.view.account.AccountFragment;
import joana.test.gh_kanban.view.explore.ExploreFragment;
import joana.test.gh_kanban.view.kanbanboard.KanbanActivity;
import joana.test.gh_kanban.view.local.LocalFragment;
import joana.test.gh_kanban.viewmodel.ExploreViewModel;
import joana.test.gh_kanban.viewmodel.LocalViewModel;

public class MainActivity extends AppCompatActivity {

    private ExploreViewModel mExploreViewModel;
    private LocalViewModel mLocalViewModel;

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

        final BottomNavigationView bottomNavigation = findViewById(R.id.navigationView);
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationViewListener);

        loadFragment(ExploreFragment.newInstance(), ExploreFragment.TAG, false);

        // Listen to single click events on fragments
        mExploreViewModel = ViewModelProviders.of(this).get(ExploreViewModel.class);
        mExploreViewModel.onRepoClick().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                bottomNavigation.setSelectedItemId(R.id.nav_local);
            }
        });

        mLocalViewModel = ViewModelProviders.of(this).get(LocalViewModel.class);
        mLocalViewModel.getSelectedBoard().observe(this, new Observer<Board>() {
            @Override
            public void onChanged(@Nullable Board board) {
                //start Kanban activity
                Intent intent = new Intent(MainActivity.this, KanbanActivity.class);
                intent.putExtra(KanbanActivity.EXTRA_BOARDID,board.getId().intValue());
                intent.putExtra(KanbanActivity.EXTRA_BOARDNAME, board.getName());
                startActivity(intent);
            }
        });
    }


}
