package joana.test.gh_kanban.view.explore;

import android.content.ClipData;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import joana.test.gh_kanban.BR;
import joana.test.gh_kanban.R;
import joana.test.gh_kanban.model.Repo;
import joana.test.gh_kanban.viewmodel.ExploreViewModel;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ExploreViewHolder> {

    private List<Repo> repoList = new ArrayList<>();
    private ExploreViewModel mViewModel;


    public ExploreAdapter(ExploreViewModel viewModel) {
        //this.context = context;
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
        holder.bind(repoList.get(position));
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    public void setRepoList(List<Repo> repoList) {
        this.repoList = repoList;
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

