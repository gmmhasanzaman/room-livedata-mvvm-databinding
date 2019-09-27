package com.example.mvvm.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.R;
import com.example.mvvm.databinding.NoteItemBinding;
import com.example.mvvm.model.Note;

import java.util.ArrayList;
import java.util.List;

import static androidx.databinding.DataBindingUtil.inflate;
import static com.example.mvvm.R.layout.note_item;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    /* private Context context;
     private List<Note> noteList;*/
    private List<Note> noteList = new ArrayList<>();
    private OnItemClickListener listener;


 /*  public NoteAdapter(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
    }*/

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        NoteItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.note_item, parent, false);
        return new ViewHolder(binding);

        /*View itemView = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false);

        //View view = LayoutInflater.from(parent.getContext()).inflate(note_item,parent,false);

        return new ViewHolder(itemView);*/


    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {

        Note currentNote = noteList.get(position);

        /*holder.titleTV.setText(currentNote.getTitle());
        holder.descriptionTV.setText(currentNote.getDescription());
        holder.priorityTV.setText(String.valueOf(currentNote.getPriority()));*/

        holder.binding.titleTV.setText(currentNote.getTitle());
        holder.binding.descriptionTV.setText(currentNote.getDescription());
        holder.binding.priorityTV.setText(String.valueOf(currentNote.getPriority()));


    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void setNotes(List<Note> noteList) {
        this.noteList = noteList;
        notifyDataSetChanged();
    }
    public Note getNoteAt(int position){
        return noteList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private NoteItemBinding binding;

        public ViewHolder(@NonNull NoteItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

           binding.getRoot().setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   int position = getAdapterPosition();
                   if (listener != null && position != RecyclerView.NO_POSITION){
                       listener.onItemClicked(noteList.get(position));
                   }
               }
           });
        }
    }

    public interface OnItemClickListener{
        void onItemClicked(Note note);
    }
    public void setOnItemClickedListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
