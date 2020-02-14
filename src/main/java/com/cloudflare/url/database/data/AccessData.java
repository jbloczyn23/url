package com.cloudflare.url.database.data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "access")
public class AccessData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shorturl;

    private LocalDateTime accesstimestamp;

    public String getShorturl() {
        return shorturl;
    }

    public void setShorturl(String shorturl) {
        this.shorturl = shorturl;
    }

    public LocalDateTime getAccesstimestamp() {
        return accesstimestamp;
    }

    public void setAccesstimestamp(LocalDateTime accesstimestamp) {
        this.accesstimestamp = accesstimestamp;
    }
}
