package com.galleryapp.service;

import com.galleryapp.exception.NotFoundImageException;
import com.galleryapp.exception.NotFoundPageException;
import com.galleryapp.exception.NotFoundViewerException;
import com.galleryapp.model.Image;
import com.galleryapp.model.Page;
import com.galleryapp.model.PageView;
import com.galleryapp.model.Viewer;
import com.galleryapp.repository.ImageDAO;
import com.galleryapp.repository.PageDAO;
import com.galleryapp.repository.PageViewDAO;
import com.galleryapp.repository.ViewerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

/**
 * Service for PageView
 */
@Service
public class PageViewService {

    private ImageDAO imageDAO;

    private PageDAO pageDAO;

    private PageViewDAO pageViewDAO;

    private ViewerDAO viewerDAO;

    @Autowired
    public void setImageDAO(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }

    @Autowired
    public void setPageDAO(PageDAO pageDAO) {
        this.pageDAO = pageDAO;
    }

    @Autowired
    public void setPageViewDAO(PageViewDAO pageViewDAO) {
        this.pageViewDAO = pageViewDAO;
    }

    @Autowired
    public void setViewerDAO(ViewerDAO viewerDAO) {
        this.viewerDAO = viewerDAO;
    }

    /**
     * register/update the page view
     */
    public void registerView(String ip, String url) throws NotFoundImageException {
        //check if url is valid finding the image
        Image image = null;
        if (!url.equals("/")) {
           image = imageDAO.findByName(url);
           if (image == null) {
               throw new NotFoundImageException();
           }
        }
        //get the page or create if dont exists.
        Page page = pageDAO.findByUrl(url);
        if (page == null) {
           page = new Page();
           page.setUrl(url);
           page.setImage(image);
           pageDAO.save(page);
        }
        //create viewer if doesnt exists
        Viewer viewer = viewerDAO.findByIp(ip);
        if (viewer == null) {
            viewer = new Viewer();
            viewer.setCreatedDate(new Date());
            viewer.setLastVisitDate(new Date());
            PageView pageView = new PageView();
            pageView.setViewer(viewer);
            pageView.setPage(page);
            pageView.setComments(new ArrayList<>());
            pageView.setNumViews(0L);
            pageViewDAO.save(pageView);
        } else {
            viewer.setLastVisitDate(new Date());
            PageView pageView = pageViewDAO.findByViewerAndPage(viewer, page);
            pageView.setNumViews(pageView.getNumViews() + 1);
            viewerDAO.save(viewer);
        }
    }

    /**
     * find the pageView
     * @param ip ip of the viewer
     * @param url url of the page
     * @return the pageView
     */
    public PageView findPageView(String ip, String url)
            throws NotFoundViewerException, NotFoundPageException {
       Page page = pageDAO.findByUrl(url);
       if (page == null ) {
           throw new NotFoundPageException();
       }
       Viewer viewer = viewerDAO.findByIp(ip);
       if (viewer == null)
           throw new NotFoundViewerException();
       return pageViewDAO.findByViewerAndPage(viewer,page);
    }

}
