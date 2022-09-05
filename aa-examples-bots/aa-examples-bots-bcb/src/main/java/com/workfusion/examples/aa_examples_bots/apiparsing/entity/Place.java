package com.workfusion.examples.aa_examples_bots.apiparsing.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Place {

    @XmlAttribute(name = "place_id")
    private String placeId;

    @XmlAttribute(name = "osm_type")
    private String osmType;

    @XmlAttribute(name = "osm_id")
    private String osmId;

    @XmlAttribute(name = "place_rank")
    private String placeRank;

    @XmlAttribute(name = "address_rank")
    private String addressRank;

    @XmlAttribute(name = "boundingbox")
    private String boundingBox;

    @XmlAttribute(name = "lat")
    private String lat;

    @XmlAttribute(name = "lon")
    private String lon;

    @XmlAttribute(name = "display_name")
    private String DisplayName;

    @XmlAttribute(name = "class")
    private String classAttr;

    @XmlAttribute(name = "type")
    private String type;

    @XmlAttribute(name = "importance")
    private String importance;


    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getOsmType() {
        return osmType;
    }

    public void setOsmType(String osmType) {
        this.osmType = osmType;
    }

    public String getOsmId() {
        return osmId;
    }

    public void setOsmId(String osmId) {
        this.osmId = osmId;
    }

    public String getPlaceRank() {
        return placeRank;
    }

    public void setPlaceRank(String placeRank) {
        this.placeRank = placeRank;
    }

    public String getAddressRank() {
        return addressRank;
    }

    public void setAddressRank(String addressRank) {
        this.addressRank = addressRank;
    }

    public String getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(String boundingBox) {
        this.boundingBox = boundingBox;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getClassAttr() {
        return classAttr;
    }

    public void setClassAttr(String classAttr) {
        this.classAttr = classAttr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }
}
