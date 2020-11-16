package com.example.takenotebyjava;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Note.class , version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateDBAsyncTask(instance).execute();
        }
    };
    private static class populateDBAsyncTask extends AsyncTask<Void,Void, Void>{

        private NoteDao noteDao;

        public populateDBAsyncTask(NoteDatabase db) {
            this.noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("title 1","Description 1",1));
            noteDao.insert(new Note("title 2","Description 2",2));
            noteDao.insert(new Note("title 3","Description 3",3));
            noteDao.insert(new Note("title 4","Description 4",4));
            return null;
        }
    }
}
