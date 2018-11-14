package joana.test.gh_kanban.view.explore;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ProgressBar;

import java.util.List;

import joana.test.gh_kanban.R;
import joana.test.gh_kanban.model.remote.Repo;
import joana.test.gh_kanban.viewmodel.ExploreViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExploreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExploreFragment extends Fragment {
    public static final String TAG = "ExploreFragment";

    private ExploreViewModel mExploreViewModel;
    private ExploreAdapter mAdapter;
    private ProgressBar mProgressBar;


    public ExploreFragment() {
        // Required empty public constructor
    }

    public static ExploreFragment newInstance() {
        return new ExploreFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mExploreViewModel.loadData();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mExploreViewModel = ViewModelProviders.of(getActivity()).get(ExploreViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.explore_recycler_view);

        mAdapter = new ExploreAdapter(mExploreViewModel);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1, GridLayout.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        mProgressBar = view.findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.VISIBLE);

        mExploreViewModel.getRepos().observe(this, new Observer<List<Repo>>() {
            @Override
            public void onChanged(@Nullable List<Repo> repoList) {
                mProgressBar.setVisibility(View.GONE);
                if(repoList != null)
                    mAdapter.setRepoList(repoList);
            }
        });

        return view;
    }

}
