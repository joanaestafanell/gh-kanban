package joana.test.gh_kanban.view.local;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import joana.test.gh_kanban.BR;
import joana.test.gh_kanban.R;
import joana.test.gh_kanban.model.local.Board;
import joana.test.gh_kanban.viewmodel.LocalViewModel;

public class LocalAdapter extends RecyclerView.Adapter<LocalAdapter.LocalViewHolder> {

    private List<Board> mBoardList = new ArrayList<>();
    private LocalViewModel mViewModel;


    public LocalAdapter(LocalViewModel viewModel) {
        this.mViewModel = viewModel;
    }

    @NonNull
    @Override
    public LocalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.board_card, parent, false);
        return new LocalViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalViewHolder holder, int position) {
        holder.bind(mBoardList.get(position));
    }

    public void setBoardList(List<Board> boardList) {
        this.mBoardList = boardList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mBoardList.size();
    }


    class LocalViewHolder extends RecyclerView.ViewHolder{
        private final ViewDataBinding binding;

        public LocalViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Board board){
            binding.setVariable(BR.board, board);
            binding.setVariable(BR.viewmodel, mViewModel);
            binding.executePendingBindings();
        }

    }
}
