package com.galleryapp.repository;

public interface PageViewCustomDAO {

    /**
     * get totals of likes of the url
     *
     * @param url page url
     * @return  total of likes
     */
     Long getTotalLikes(String url);

    /**
     * get total of unlikes of the url
     *
     * @param url page url
     * @return total of unlikes
     */
    Long getTotalUnlikes(String url);

    /***
     * get total of views of the url
     *
     * @param url page url
     * @return total of views
     */
    Long getTotalViews(String url);

     /**
     * get totals of likes of site
     *
     * @return  total of likes
     */
     Long getTotalLikes();

    /**
     * get total of unlikes of site
     *
     * @return total of unlikes
     */
    Long getTotalUnlikes();

    /***
     * get total of views of site
     *
     * @return total of views
     */
    Long getTotalViews();

}
