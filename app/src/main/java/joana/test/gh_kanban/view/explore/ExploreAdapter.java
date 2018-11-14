package joana.test.gh_kanban.view.explore;

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
import joana.test.gh_kanban.model.remote.Repo;
import joana.test.gh_kanban.viewmodel.ExploreViewModel;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ExploreViewHolder> {

    private List<Repo> mRepoList = new ArrayList<>();
    private ExploreViewModel mViewModel;


    public ExploreAdapter(ExploreViewModel viewModel) {
        this.mViewModel = viewModel;
    }

    @NonNull
    @Override
    public ExploreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.remote_repo_card, parent, false);
        return new ExploreViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreViewHolder holder, int position) {
        holder.bind(mRepoList.get(position));
    }

    @Override
    public int getItemCount() {
        return mRepoList.size();
    }

    public void setRepoList(List<Repo> repoList) {
        this.mRepoList = repoList;
        notifyDataSetChanged();
    }

    class ExploreViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        public ExploreViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Repo item) {
            binding.setVariable(BR.repo, item);
            binding.setVariable(BR.viewmodel, mViewModel);
            binding.executePendingBindings();

        }
    }
}

