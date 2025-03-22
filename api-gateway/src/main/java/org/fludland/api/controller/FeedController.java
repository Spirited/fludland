package org.fludland.api.controller;

import org.fludland.service.PostDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/my")
public class FeedController {
    @GetMapping("/feed")
    public ResponseEntity<List<PostDto>> myFeed() {
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/recommends")
    public ResponseEntity<List<PostDto>> myRecommends() {
        return ResponseEntity.ok(Collections.emptyList());
    }
}
