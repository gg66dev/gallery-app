package com.galleryapp.repository;


import com.galleryapp.model.Image;
import com.galleryapp.model.Page;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:gallery-app-servlet.xml"})
public class PageDAOTest {

    private PageDAO pageDAO;

    private List<Page> pages;

    @Autowired
    public void setPageDAO(PageDAO pageDAO) {
        this.pageDAO = pageDAO;
    }

    @Before
    public void init() {
        pages = new ArrayList<>();
    }

    @After
    public void deletAllPage() {
        for (Page page : pages) {
            pageDAO.delete(page);
        }
    }

    @Test( expected = org.springframework.dao.DataIntegrityViolationException.class  )
    public void testCreateImageWithoutName() {
        Page page = new Page();
        pageDAO.save(page);
    }

    @Test
    public void testCreateAPage() {
        Page page = new Page();
        Image image = new Image();
        image.setName("test image");
        page.setImage(image);
        page.setUrl("/home");
        pageDAO.save(page);
        Iterable<Page> iterable = pageDAO.findAll();
        Iterator it = iterable.iterator();
        Page dbPage = (Page)it.next();
        assertThat(dbPage.getId(), is(notNullValue()));
        assertThat(dbPage.getImage().getId(), is(notNullValue()));
        pages.add(page);
    }

    @Test
    public void testFindByUrl() {
         Page page = new Page();
        Image image = new Image();
        image.setName("test image");
        page.setImage(image);
        page.setUrl("/home");
        pageDAO.save(page);

        Page dbPage = pageDAO.findByUrl("/home");
        assertThat(dbPage.getUrl(), equalTo(page.getUrl()));
        pages.add(page);
    }

}
