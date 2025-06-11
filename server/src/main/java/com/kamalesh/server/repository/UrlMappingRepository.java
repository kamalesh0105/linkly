package com.kamalesh.server.repository;

import com.kamalesh.server.dtos.UrlMappingDTO;
import com.kamalesh.server.models.UrlMapping;
import com.kamalesh.server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping,Long> {
    UrlMapping findByShortUrl(String shortUrl);
    List<UrlMapping> findByUser(User user);


}
