package com.galleryapp.repository;

import com.galleryapp.model.Viewer;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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
public class ViewerDAOTest {

    private ViewerDAO viewerDAO;

    private List<Viewer> viewers;

    @Autowired
    public void setViewerDAO(ViewerDAO viewerDAO) {
        this.viewerDAO = viewerDAO;
    }

    @Before
    public void init() {
        viewers = new ArrayList<>();
    }

    @After
    public void deletAllViewer() {
        for (Viewer viewer : viewers) {
            viewerDAO.delete(viewer);
        }
    }

    @Test( expected = org.springframework.dao.DataIntegrityViolationException.class  )
    public void testCreateViewerWithoutIp() {
        Viewer viewer = new Viewer();
        viewerDAO.save(viewer);
    }

    @Test
    public void testCreateAViewer() {
        Viewer viewer = new Viewer();
        viewer.setIp("111.111.111.111");
        viewerDAO.save(viewer);
        Iterable<Viewer> iterable = viewerDAO.findAll();
        Iterator it = iterable.iterator();
        Viewer dbViewer = (Viewer)it.next();
        assertThat(dbViewer.getId(), is(notNullValue()));
        viewers.add(viewer);
    }

    @Test
    public void testFindByIp() {
        Viewer viewer = new Viewer();
        viewer.setIp("111.111.111.111");
        viewerDAO.save(viewer);
        Viewer dbViewer = viewerDAO.findByIp("111.111.111.111");
        assertThat(dbViewer.getIp(), equalTo(viewer.getIp()));
        viewers.add(viewer);
    }

}
