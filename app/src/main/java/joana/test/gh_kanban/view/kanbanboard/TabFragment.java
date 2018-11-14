package joana.test.gh_kanban.view.kanbanboard;

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
import android.widget.TextView;

import java.util.List;

import joana.test.gh_kanban.R;
import joana.test.gh_kanban.model.local.BoardIssue;
import joana.test.gh_kanban.util.Utils;
import joana.test.gh_kanban.viewmodel.KanbanViewModel;

public class TabFragment extends Fragment {

    private static final String ARG_BOARD_ID = "arg_board_id";
    private static final String ARG_STATE = "arg_state";
    private int mBoardId;
    private int mFragmentState;
    private KanbanViewModel mKanbanViewModel;
    private KanbanAdapter mAdapter;

    public TabFragment() {
        // Required empty public constructor
    }

    public static TabFragment newInstance(int boardId, int fragmentState){
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_BOARD_ID, boardId);
        args.putInt(ARG_STATE, fragmentState);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mBoardId = getArguments().getInt(ARG_BOARD_ID);
            mFragmentState = getArguments().getInt(ARG_STATE);
        }
        mKanbanViewModel = ViewModelProviders.of(this).get(KanbanViewModel.class);
        String state = Utils.getKanbanStateByIndex(mFragmentState);

        mKanbanViewModel.initBoardIssues(mBoardId, state);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);

        final TextView emptyMsg = view.findViewById(R.id.empty_textview);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        mAdapter = new KanbanAdapter(mKanbanViewModel);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1, GridLayout.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        mKanbanViewModel.getIssueList().observe(this, new Observer<List<BoardIssue>>() {
            @Override
            public void onChanged(@Nullable List<BoardIssue> boardIssues) {
                if(boardIssues != null && boardIssues.size() > 0) {
                    emptyMsg.setVisibility(View.GONE);
                    mAdapter.setIssueList(boardIssues);
                }else emptyMsg.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }
}
