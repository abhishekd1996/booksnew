package com.dilipkumarg.netent.bookstore.helpers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.dilipkumarg.netent.bookstore.data.entities.Book;
import com.dilipkumarg.netent.bookstore.data.models.MediaPost;

@Component
public class DemoMediaCoverageProvider implements MediaCoverageProvider {

    private final RestTemplate restTemplate;
    @Value("${app.media.demo.url}")
    private String url;

    public DemoMediaCoverageProvider(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<MediaPost> search(final Book book) {
        return Objects.requireNonNull(restTemplate.getForObject(url, DemoMediaPosts.class)).stream()
                .filter(post -> post.getTitle().contains(book.getTitle()) || post.getBody().contains(book.getTitle()))
                .map(post -> new MediaPost(post.getTitle(), url))
                .collect(Collectors.toList());
    }
}
