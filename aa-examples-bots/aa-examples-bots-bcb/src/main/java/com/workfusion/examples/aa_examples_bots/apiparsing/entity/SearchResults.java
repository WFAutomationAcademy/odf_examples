package com.workfusion.examples.aa_examples_bots.apiparsing.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "searchresults")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchResults {

    @XmlElement(name = "place")
    private List<Place> places;

    @XmlAttribute(name = "timestamp")
    private String timestamp;

    @XmlAttribute(name = "attribution")
    private String attribution;

    @XmlAttribute(name = "querystring")
    private String querystring;

    @XmlAttribute(name = "exclude_place_ids")
    private String exclude_place_ids;

    @XmlAttribute(name = "more_url")
    private String more_url;

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public String getQuerystring() {
        return querystring;
    }

    public void setQuerystring(String querystring) {
        this.querystring = querystring;
    }

    public String getExclude_place_ids() {
        return exclude_place_ids;
    }

    public void setExclude_place_ids(String exclude_place_ids) {
        this.exclude_place_ids = exclude_place_ids;
    }

    public String getMore_url() {
        return more_url;
    }

    public void setMore_url(String more_url) {
        this.more_url = more_url;
    }
}
