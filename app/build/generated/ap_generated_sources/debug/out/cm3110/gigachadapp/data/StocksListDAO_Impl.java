package cm3110.gigachadapp.data;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class StocksListDAO_Impl implements StocksListDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<StocksList> __insertionAdapterOfStocksList;

  private final EntityDeletionOrUpdateAdapter<StocksList> __deletionAdapterOfStocksList;

  private final SharedSQLiteStatement __preparedStmtOfDeleteStock;

  public StocksListDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStocksList = new EntityInsertionAdapter<StocksList>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `StocksList` (`description`,`symptomHeader`,`symptoms`,`name`,`uid`) VALUES (?,?,?,?,nullif(?, 0))";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, StocksList value) {
        if (value.getDescription() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getDescription());
        }
        if (value.getSymptomHeader() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getSymptomHeader());
        }
        if (value.getSymptoms() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getSymptoms());
        }
        if (value.getName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getName());
        }
        stmt.bindLong(5, value.getUid());
      }
    };
    this.__deletionAdapterOfStocksList = new EntityDeletionOrUpdateAdapter<StocksList>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `StocksList` WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, StocksList value) {
        stmt.bindLong(1, value.getUid());
      }
    };
    this.__preparedStmtOfDeleteStock = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM StocksList WHERE name = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final StocksList stocksList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfStocksList.insert(stocksList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(final List<StocksList> stocksList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfStocksList.insert(stocksList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final StocksList stocksList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfStocksList.handle(stocksList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final List<StocksList> stocksList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfStocksList.handleMultiple(stocksList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteStock(final String name) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteStock.acquire();
    int _argIndex = 1;
    if (name == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, name);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteStock.release(_stmt);
    }
  }

  @Override
  public LiveData<List<StocksList>> findStock(final String name) {
    final String _sql = "SELECT * FROM StocksList WHERE name = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"StocksList"}, false, new Callable<List<StocksList>>() {
      @Override
      public List<StocksList> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSymptomHeader = CursorUtil.getColumnIndexOrThrow(_cursor, "symptomHeader");
          final int _cursorIndexOfSymptoms = CursorUtil.getColumnIndexOrThrow(_cursor, "symptoms");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
          final List<StocksList> _result = new ArrayList<StocksList>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final StocksList _item;
            _item = new StocksList();
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            _item.setDescription(_tmpDescription);
            final String _tmpSymptomHeader;
            _tmpSymptomHeader = _cursor.getString(_cursorIndexOfSymptomHeader);
            _item.setSymptomHeader(_tmpSymptomHeader);
            final String _tmpSymptoms;
            _tmpSymptoms = _cursor.getString(_cursorIndexOfSymptoms);
            _item.setSymptoms(_tmpSymptoms);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item.setName(_tmpName);
            final int _tmpUid;
            _tmpUid = _cursor.getInt(_cursorIndexOfUid);
            _item.setUid(_tmpUid);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
