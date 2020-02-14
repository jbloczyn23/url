package com.cloudflare.url.database.repository;

import com.cloudflare.url.database.data.AccessData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface AccessRepository extends JpaRepository<AccessData, Long> {

    List<AccessData> findAllByShorturl(String shortUrl);
    List<AccessData> findAllByShorturlAndAccesstimestampAfter(String shortUrl, LocalDateTime localDateTime);
}
