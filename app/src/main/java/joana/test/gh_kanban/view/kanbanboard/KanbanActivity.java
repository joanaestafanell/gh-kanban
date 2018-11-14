package joana.test.gh_kanban.view.kanbanboard;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;


import joana.test.gh_kanban.R;
import joana.test.gh_kanban.util.Constants;

public class KanbanActivity extends AppCompatActivity {

    public static String EXTRA_BOARDID = "extra_boardid";
    public static String EXTRA_BOARDNAME = "extra_boardname";

    private ViewPager mViewPager;
    private TabAdapter mAdapter;
    private TabLayout mTabLayout;
    private int mBoardId;
    private String mBoardName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mBoardId = getIntent().getIntExtra(EXTRA_BOARDID, -1);
        mBoardName = getIntent().getStringExtra(EXTRA_BOARDNAME);

        setContentView(R.layout.activity_kanban);

        initToolbar();

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mAdapter = new TabAdapter(getSupportFragmentManager());
        mAdapter.addFragment(TabFragment.newInstance(mBoardId, 0), Constants.KanbanState.BACKLOG.toString());
        mAdapter.addFragment(TabFragment.newInstance(mBoardId, 1), Constants.KanbanState.NEXT.toString());
        mAdapter.addFragment(TabFragment.newInstance(mBoardId, 2), Constants.KanbanState.DOING.toString());
        mAdapter.addFragment(TabFragment.newInstance(mBoardId, 3), Constants.KanbanState.DONE.toString());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String title = (mBoardName != null) ? mBoardName : getString(R.string.title_activity_kanban);
        getSupportActionBar().setTitle(title);

    }


}
