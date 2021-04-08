package cn.stu.lab4.model.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface HolidayDao {

    @Query("SELECT * FROM HolidayDbEntity ORDER BY name COLLATE NOCASE")
    List<HolidayDbEntity> getRepositories();

    @Query("SELECT * FROM HolidayDbEntity WHERE date = :date")
    HolidayDbEntity getByDate(String date);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHolidays(List<HolidayDbEntity> repositories);

    @Query("DELETE FROM HolidayDbEntity")
    void deleteHoliday();

    @Transaction
    default void updateHolidayByName(List<HolidayDbEntity> entities) {
        deleteHoliday();
        insertHolidays(entities);
    }

}
