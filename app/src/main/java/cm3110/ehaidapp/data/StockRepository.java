package cm3110.gigachadapp.data;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class StockRepository {



    /**
     * The Singleton instance for this repository
     */
    private static StockRepository INSTANCE;

    private Context context;

    private StocksListDAO stocksListDAO;

    private LiveData<List<StocksList>> stockList;

    private StockRepository(Context context) {
        super();
        this.context = context;
        stocksListDAO = StockDatabase.getDatabase(context).cryptoDAO();
        stockList = null;
    }


    public static StockRepository getRepository(Context context) {
        if (INSTANCE == null) {
            synchronized (StockRepository.class) {
                if (INSTANCE == null)
                    INSTANCE = new StockRepository(context);
            }
        }
        return INSTANCE;
    }



    public LiveData<List<StocksList>> getStockDataFromDB(String stock) {
        stockList = stocksListDAO.findStock(stock);
        return stockList;
    }

    /**
     * Stores stocksLists in the {@link StockDatabase}
     * @param stocksLists {@link List} of {@link StocksList} instances to be stored
     */
    public void storeInDatabase(List<StocksList> stocksLists) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                // insert stocks into the database
                stocksListDAO.insert(stocksLists);
            }
        });
    }
    public void deleteSavedCrypto(String cryptoName){
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                // call the DAO to delete the data for the crypto specified name
                stocksListDAO.deleteStock(cryptoName);

            }
        });
    }

}
