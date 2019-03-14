package com.galleryapp.repository;

public interface PageViewCustomDAO {

    /**
     * get totals of likes of the url
     *
     * @param url page url
     * @return  total of likes
     */
    Integer getTotalLikes(String url);

    /**
     * get total of unlikes of the url
     *
     * @param url page url
     * @return total of unlikes
     */
    Integer getTotalUnlikes(String url);
}
