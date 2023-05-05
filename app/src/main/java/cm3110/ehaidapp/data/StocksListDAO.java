package cm3110.gigachadapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * Data Access Object for {@link StocksList} entities.
 */
@Dao
public interface StocksListDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(StocksList stocksList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(List<StocksList> stocksList);

    @Delete
    public void delete(StocksList stocksList);

    @Delete
    public void delete(List<StocksList> stocksList);

    @Query("DELETE FROM StocksList WHERE name = :name")
    public void deleteStock(String name);

    @Query("SELECT * FROM StocksList WHERE name = :name")
    public LiveData<List<StocksList>> findStock(String name);
}
