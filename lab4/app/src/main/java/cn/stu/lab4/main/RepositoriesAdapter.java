package cn.stu.lab4.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import cn.stu.lab4.R;
import cn.stu.lab4.model.Holiday;

public class RepositoriesAdapter
        extends RecyclerView.Adapter<RepositoriesAdapter.RepositoryViewHolder>
        implements View.OnClickListener {

    private List<Holiday> repositories = Collections.emptyList();
    private RepositoryListener listener;

    public RepositoriesAdapter(RepositoryListener listener) {
        setHasStableIds(true);
        this.listener = listener;
    }

    public void setRepositories(List<Holiday> repositories) {
        this.repositories = repositories;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        Holiday holiday = (Holiday) v.getTag();
        listener.onRepositoryChosen(holiday);
    }

    @Override
    public long getItemId(int position) {
        return repositories.get(position).getDate().hashCode();
    }

    @NonNull
    @Override
    public RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.item_repository, parent, false);
        return new RepositoryViewHolder(root, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryViewHolder holder, int position) {
        Holiday holiday = repositories.get(position);
        holder.nameTextView.setText(holiday.getName());
        holder.starsTextView.setText(holiday.getDate());
        holder.itemView.setTag(holiday);
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    static class RepositoryViewHolder extends RecyclerView.ViewHolder {
        private TextView starsTextView;
        private TextView nameTextView;

        public RepositoryViewHolder(@NonNull View itemView, View.OnClickListener listener) {
            super(itemView);
            starsTextView = itemView.findViewById(R.id.starsTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            itemView.setOnClickListener(listener);
        }
    }

    public interface RepositoryListener {
        void onRepositoryChosen(Holiday holiday);
    }
}
