package joana.test.gh_kanban.view.kanbanboard;

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
import joana.test.gh_kanban.model.local.BoardIssue;
import joana.test.gh_kanban.viewmodel.KanbanViewModel;


public class KanbanAdapter extends RecyclerView.Adapter<KanbanAdapter.ViewHolder> {

    private List<BoardIssue> mIssueList = new ArrayList<>();
    private KanbanViewModel mViewModel;


    public KanbanAdapter(KanbanViewModel viewModel) {
        this.mViewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.issue_card, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mIssueList.get(position));
    }

    public void setIssueList(List<BoardIssue> issueList) {
        this.mIssueList = issueList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mIssueList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        private final ViewDataBinding binding;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(BoardIssue issue){
            binding.setVariable(BR.issue, issue);
            binding.setVariable(BR.viewmodel, mViewModel);
            binding.executePendingBindings();
        }

    }
}
