package com.example.takenotebyjava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> notes = new ArrayList<>();
    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item,parent,false);

        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.title.setText(currentNote.getTitle());
        holder.discription.setText(currentNote.getDescription());
        holder.priority.setText(String.valueOf(currentNote.getPriority()));


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();

    }

    public class NoteHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView discription;
        private TextView priority;


        public NoteHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tvTitle);
            discription = itemView.findViewById(R.id.tvDiscription);
            priority = itemView.findViewById(R.id.tvPriority);
        }
    }
}