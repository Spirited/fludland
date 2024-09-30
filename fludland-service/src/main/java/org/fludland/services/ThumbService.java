package org.fludland.services;

public interface ThumbService {
    void putPostThumb(long postId, long userId);
    long getTotalPostThumbs(long postId);
}
