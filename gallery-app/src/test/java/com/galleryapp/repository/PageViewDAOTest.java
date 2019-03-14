package com.galleryapp.repository;


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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
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

        assertThat(pageView.getId(), is(notNullValue()));
        assertThat(pageView.getPage().getId(), is(notNullValue()));
        assertThat(pageView.getViewer().getId(), is(notNullValue()));
        pageViews.add(pageView);
    }

    //test to add comment

    //test to like page

    //test to unlike page

}
