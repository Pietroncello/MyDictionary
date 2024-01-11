package com.example.mydictionary.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydictionary.Models.Definitions;
import com.example.mydictionary.R;
import com.example.mydictionary.ViewHolders.DefinitionViewHolder;

import java.util.List;

public class DefinitionAdapter extends RecyclerView.Adapter<DefinitionViewHolder>{
    private Context context;
    private List<Definitions> definitionsList;

    public DefinitionAdapter(Context context, List<Definitions> definitionsList) {
        this.context = context;
        this.definitionsList = definitionsList;
    }

    @NonNull
    @Override
    public DefinitionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DefinitionViewHolder(LayoutInflater.from(context).inflate(R.layout.definitions_list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DefinitionViewHolder holder, int position) {
        holder.textView_definition.setText("Definition: " + definitionsList.get(position).getDefinition());
        holder.textView_example.setText("Example: " + definitionsList.get(position).getExample());

    }

    @Override
    public int getItemCount() {
        return definitionsList.size();
    }
}
