package org.fludland.services.impl;

import org.fludland.repositories.ThumbRepository;
import org.fludland.services.ThumbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThumbServiceImpl implements ThumbService {
    private final ThumbRepository thumbRepository;

    @Autowired
    public ThumbServiceImpl(ThumbRepository thumbRepository) {
        this.thumbRepository = thumbRepository;
    }

    @Override
    public void putPostThumb(long postId, long userId) {

    }

    @Override
    public long getTotalPostThumbs(long postId) {
        return 0;
    }
}
