package com.cloudflare.url.controller;

import java.time.LocalDateTime;
import java.util.List;

public class AccessModel {

    private String url;

    private String timePeriod;

    private int timesAccessed;

    private List<LocalDateTime> timestamps;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public int getTimesAccessed() {
        return timesAccessed;
    }

    public void setTimesAccessed(int timesAccessed) {
        this.timesAccessed = timesAccessed;
    }

    public List<LocalDateTime> getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(List<LocalDateTime> timestamps) {
        this.timestamps = timestamps;
    }
}
