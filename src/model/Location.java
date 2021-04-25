package model;

public final class Location {
    private final String address, city, state, zipCode, country;

    public Location(String address, String city, String state, String zipCode, String country) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }


    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }

}