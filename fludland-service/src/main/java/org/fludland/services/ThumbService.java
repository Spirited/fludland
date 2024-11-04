package org.fludland.services;

public interface ThumbService {
    void putPostThumb(long postId, int userId);
    long getTotalPostThumbs(long postId);
}
