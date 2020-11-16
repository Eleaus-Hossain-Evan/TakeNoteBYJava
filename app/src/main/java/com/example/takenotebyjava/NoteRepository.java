package com.example.takenotebyjava;

import android.app.Application;
import android.os.AsyncTask;

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
        new insertNoteAsyncTask(noteDao).execute(note);
    }
    public void update(Note note){
        new updateNoteAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note){
        new deleteNoteAsyncTask(noteDao).execute(note);
    }
    public void deleteAllNotes(){
        new deleteAllNotesNoteAsyncTask(noteDao).execute();
    }



    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }



    private static class insertNoteAsyncTask extends AsyncTask<Note, Void, Void>{

        private NoteDao noteDao;

        private insertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class updateNoteAsyncTask extends AsyncTask<Note, Void, Void>{

        private NoteDao noteDao;

        private updateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class deleteNoteAsyncTask extends AsyncTask<Note, Void, Void>{

        private NoteDao noteDao;

        private deleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class deleteAllNotesNoteAsyncTask extends AsyncTask<Void, Void, Void>{

        private NoteDao noteDao;

        private deleteAllNotesNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
