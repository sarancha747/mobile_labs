package cn.stu.lab4.model.network;

public class RepositoryNetworkEntity {
    private String date;
    private String localName;
    private String name;
    private String countryCode;
    private boolean fixed;
    private boolean global;
    private int launchYear;

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
