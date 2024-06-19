package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NASAAnswer {
    String copyright;
    String date;
    String explanation;
    String hdurl;
    String media_type;
    String service_version;
    String title;
    String url;

    public NASAAnswer(@JsonProperty("url") String url,
                      @JsonProperty("title") String title,
                      @JsonProperty("service_version") String service_version,
                      @JsonProperty("media_type") String media_type,
                      @JsonProperty("hdurl") String hdurl,
                      @JsonProperty("explanation") String explanation,
                      @JsonProperty("date") String date,
                      @JsonProperty("copyright") String copyright)
    {
        this.url = url;
        this.title = title;
        this.service_version = service_version;
        this.media_type = media_type;
        this.hdurl = hdurl;
        this.explanation = explanation;
        this.date = date;
        this.copyright = copyright;
    }
}
