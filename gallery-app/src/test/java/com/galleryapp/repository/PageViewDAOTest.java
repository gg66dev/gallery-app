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

    private PageDAO pageDAO;

    private ViewerDAO viewerDAO;

    private List<PageView> pageViews;

    private List<Page> pages;

    private List<Viewer> viewers;

    @Autowired
    public void setPageViewDAO(PageViewDAO pageViewDAO) {
        this.pageViewDAO = pageViewDAO;
    }

    @Autowired
    public void setPageDAO(PageDAO pageDAO) {
        this.pageDAO = pageDAO;
    }

    @Autowired
    public void setViewers(ViewerDAO viewerDAO) { this.viewerDAO = viewerDAO; }

    @Before
    public void init() {
        pageViews = new ArrayList<>();
        pages = new ArrayList<>();
        viewers = new ArrayList<>();
    }

    @After
    public void deletAllPageView() {
        for (PageView pageView : pageViews ) {
            pageViewDAO.delete(pageView);
        }
        for (Page page : pages) {
            pageDAO.delete(page);
        }
        for (Viewer viewer : viewers) {
            viewerDAO.delete(viewer);
        }
    }

    @Test
    public void testCreatePageView () {
        Page page = new Page();
        page.setUrl("/home");
        pageDAO.save(page);
        pages.add(page);
        Viewer viewer = new Viewer();
        viewer.setIp("111.111.111.111");
        viewerDAO.save(viewer);
        viewers.add(viewer);
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
        pageDAO.save(page);
        pages.add(page);
        PageView pageView = createAndSaveVieaAndPageView("111.111.111.111", page);
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


    private PageView createAndSaveVieaAndPageView(String ip, Page page) {
        Viewer viewer = new Viewer();
        viewer.setIp(ip);
        viewerDAO.save(viewer);
        viewers.add(viewer);
        PageView pageView = new PageView();
        pageView.setPage(page);
        pageView.setViewer(viewer);
        pageViewDAO.save(pageView);
        pageViews.add(pageView);
        return pageView;
    }

    @Transactional
    @Test
    public void testCountLikes() {
        String targetPageUrl = "myPage";
        createAndSavePage(targetPageUrl);
        Page dbPage = pageDAO.findByUrl(targetPageUrl);
        PageView pageViewOne = createAndSaveVieaAndPageView("111.111.111.111",dbPage);
        PageView pageViewTwo = createAndSaveVieaAndPageView("111.111.111.222",dbPage);
        PageView pageViewThree = createAndSaveVieaAndPageView("111.111.111.333",dbPage);
        createAndSaveVieaAndPageView("111.111.111.444", dbPage);
        pageViewOne.setLike(true);
        pageViewTwo.setLike(true);
        pageViewThree.setLike(true);
        pageViewDAO.save(pageViewOne);
        pageViewDAO.save(pageViewTwo);
        pageViewDAO.save(pageViewThree);
        pageViewDAO.save(pageViewOne);
        Long likes = pageViewDAO.getTotalLikes(targetPageUrl);
        assertThat(likes, equalTo(3L));
    }

    private void createAndSavePage(String url) {
        Page page = new Page();
        page.setUrl(url);
        pageDAO.save(page);
        pages.add(page);
    }

    @Test
    public void testCountUnlikes () {
        String targetPageUrl = "myPage";
        Page page = new Page();
        page.setUrl(targetPageUrl);
        pageDAO.save(page);
        pages.add(page);
        Page dbPage = pageDAO.findByUrl(targetPageUrl);
        PageView pageViewOne = createAndSaveVieaAndPageView("111.111.111.111",dbPage);
        PageView pageViewTwo = createAndSaveVieaAndPageView("111.111.111.222",dbPage);
        PageView pageViewThree = createAndSaveVieaAndPageView("111.111.111.333",dbPage);
        createAndSaveVieaAndPageView("111.111.111.444", dbPage);
        pageViewOne.setUnlike(true);
        pageViewTwo.setUnlike(true);
        pageViewThree.setUnlike(true);
        pageViewDAO.save(pageViewOne);
        pageViewDAO.save(pageViewTwo);
        pageViewDAO.save(pageViewThree);
        Long unlikes = pageViewDAO.getTotalUnlikes(targetPageUrl);
        assertThat(unlikes, equalTo(3L));
    }

}
