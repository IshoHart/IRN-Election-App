package com.arsabi.ishtech.irnelectionreportingapp.adapter;

/**
 * Created by Isho on 2/10/2018.
 */

public class Vote {

    private String Name;
    private String Vote;
    private String Region;
    private String District;
    private String party;
    private String center;
    private String station;
    private String Const;
    private String Ward;

    public Vote(String name, String vote, String region, String district, String party, String center, String station, String aConst, String ward) {
        Name = name;
        Vote = vote;
        Region = region;
        District = district;
        this.party = party;
        this.center = center;
        this.station = station;
        Const = aConst;
        Ward = ward;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getVote() {
        return Vote;
    }

    public void setVote(String vote) {
        Vote = vote;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getConst() {
        return Const;
    }

    public void setConst(String aConst) {
        Const = aConst;
    }

    public String getWard() {
        return Ward;
    }

    public void setWard(String ward) {
        Ward = ward;
    }
}
