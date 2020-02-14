package com.cloudflare.url.database.repository;

import com.cloudflare.url.database.data.UrlData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UrlRepository extends JpaRepository<UrlData, Long> {

    UrlData findByShorturl(String shortUrl);
}
