package com.example.takenotebyjava;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note){

    }
    public void update(Note note){

    }

    public void delete(Note note){

    }
    public void deleteAllNotes(){

    }
    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }


}
