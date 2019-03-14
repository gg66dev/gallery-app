package com.galleryapp.repository;


import com.galleryapp.model.Comment;
import com.galleryapp.model.Page;
import com.galleryapp.model.PageView;
import com.galleryapp.model.Viewer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:gallery-app-servlet.xml"})
public class PageViewDAOTest {

    private PageViewDAO pageViewDAO;

    private List<PageView> pageViews;

    @Autowired
    public void setPageViewDAO(PageViewDAO pageViewDAO) {
        this.pageViewDAO = pageViewDAO;
    }

    @Before
    public void init() {
        pageViews = new ArrayList<>();
    }

    @After
    public void deletAllPageView() {
        for (PageView pageView : pageViews ) {
            pageViewDAO.delete(pageView);
        }
    }

    @Test
    public void testCreatePageView () {
        Page page = new Page();
        page.setUrl("/home");
        Viewer viewer = new Viewer();
        viewer.setIp("111.111.111.111");
        PageView pageView = new PageView();
        pageView.setPage(page);
        pageView.setViewer(viewer);
        pageViewDAO.save(pageView);
        pageViews.add(pageView);

        assertThat(pageView.getId(), is(notNullValue()));
        assertThat(pageView.getPage().getId(), is(notNullValue()));
        assertThat(pageView.getViewer().getId(), is(notNullValue()));
    }

    @Transactional //fetch commments in pageView find method
    @Test
    public void testAddAComment() {
        Page page = new Page();
        page.setUrl("/home");
        Viewer viewer = new Viewer();
        viewer.setIp("111.111.111.111");
        PageView pageView = new PageView();
        pageView.setPage(page);
        pageView.setViewer(viewer);
        pageViewDAO.save(pageView);
        pageViews.add(pageView);

        PageView dbPageView = pageViewDAO.findByViewerAndPage(pageView.getViewer(), pageView.getPage());
        dbPageView.setComments(new ArrayList<>());
        Comment comment = new Comment();
        comment.setMessage("Hello world");
        comment.setCreatedDate(new Date());
        dbPageView.getComments().add(comment);
        pageViewDAO.save(dbPageView);
        dbPageView = pageViewDAO.findByViewerAndPage(pageView.getViewer(), pageView.getPage());
        assertThat(dbPageView.getComments().get(0).getId(), is(notNullValue()));
    }

    @Test
    public void testCountLikes() {
       Integer likes = pageViewDAO.getTotalLikes("mypage");
       assertThat(likes, equalTo(0));
    }

    @Test
    public void testCountUnlikes () {
        Integer unlikes = pageViewDAO.getTotalUnlikes("mypage");
        assertThat(unlikes, equalTo(0));
    }

}
