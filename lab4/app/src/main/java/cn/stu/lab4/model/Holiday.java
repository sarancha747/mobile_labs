package cn.stu.lab4.model;

import cn.stu.lab4.model.db.HolidayDbEntity;

public class Holiday {
    private final String date;
    private final String localName;
    private final String name;
    private final String countryCode;
    private final boolean fixed;
    private final boolean global;
    private final int launchYear;

    public Holiday(String date, String localName, String name, String countryCode, boolean fixed, boolean global, int launchYear) {
        this.date = date;
        this.localName = localName;
        this.name = name;
        this.countryCode = countryCode;
        this.fixed = fixed;
        this.global = global;
        this.launchYear = launchYear;
    }

    public Holiday(HolidayDbEntity entity) {
        this(
                entity.getDate(),
                entity.getLocalName(),
                entity.getName(),
                entity.getCountryCode(),
                entity.isFixed(),
                entity.isGlobal(),
                entity.getLaunchYear()
        );
    }

    public String getDate() {
        return date;
    }

    public String getLocalName() {
        return localName;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public boolean isFixed() {
        return fixed;
    }

    public boolean isGlobal() {
        return global;
    }

    public int getLaunchYear() {
        return launchYear;
    }
}
