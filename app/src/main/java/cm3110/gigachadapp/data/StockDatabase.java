package cm3110.gigachadapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {StocksList.class}, version = 1)
public abstract class StockDatabase extends RoomDatabase {

    public abstract StocksListDAO cryptoDAO();

    private static StockDatabase INSTANCE;

    //Gets the database//
    public static StockDatabase getDatabase(Context context){
        if (INSTANCE == null){
            synchronized (StockDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            StockDatabase.class,
                            "stock_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
