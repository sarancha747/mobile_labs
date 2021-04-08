package cn.stu.lab4.model.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import cn.stu.lab4.model.network.RepositoryNetworkEntity;

@Entity(tableName = "HolidayDbEntity")
public class HolidayDbEntity {
    @NonNull
    @PrimaryKey
    private String date;

    @ColumnInfo(name = "localName")
    private String localName;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "countryCode")
    private String countryCode;

    @ColumnInfo(name = "fixed")
    private boolean fixed;

    @ColumnInfo(name = "global")
    private boolean global;

    @ColumnInfo(name = "launchYear")
    private int launchYear;


    public HolidayDbEntity() {
    }

    public HolidayDbEntity(RepositoryNetworkEntity entity) {
        this.date = entity.getDate();
        this.localName = entity.getLocalName();
        this.name = entity.getName();
        this.countryCode = entity.getCountryCode();
        this.fixed = entity.isFixed();
        this.global = entity.isGlobal();
        this.launchYear = entity.getLaunchYear();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public int getLaunchYear() {
        return launchYear;
    }

    public void setLaunchYear(int launchYear) {
        this.launchYear = launchYear;
    }
}
