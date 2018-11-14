package joana.test.gh_kanban.view.local;

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


import java.util.List;

import joana.test.gh_kanban.R;
import joana.test.gh_kanban.model.local.Board;
import joana.test.gh_kanban.viewmodel.LocalViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LocalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocalFragment extends Fragment {
    public static final String TAG = "LocalFragment";

    private LocalViewModel mLocalViewModel;
    private LocalAdapter mAdapter;

    public LocalFragment() {
        // Required empty public constructor
    }

    public static LocalFragment newInstance() { return new LocalFragment();    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocalViewModel = ViewModelProviders.of(getActivity()).get(LocalViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_local, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.local_recycler_view);
        mAdapter = new LocalAdapter(mLocalViewModel);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1, GridLayout.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        mLocalViewModel.getBoards().observe(this, new Observer<List<Board>>() {
            @Override
            public void onChanged(@Nullable List<Board> boards) {
                if(boards != null)
                    mAdapter.setBoardList(boards);
            }
        });

        return view;
    }
}
