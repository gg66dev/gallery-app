package com.galleryapp.repository.impl;

import com.galleryapp.repository.PageViewCustomDAO;

public  class PageViewDAOImpl implements PageViewCustomDAO {

    @Override
    public Integer getTotalLikes(String url) {
        return 0;
    }

    @Override
    public Integer getTotalUnlikes(String url) {
        return 0;
    }
}
