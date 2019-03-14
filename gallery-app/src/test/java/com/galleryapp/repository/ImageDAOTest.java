package com.galleryapp.repository;

import com.galleryapp.model.Image;
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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:gallery-app-servlet.xml"})
public class ImageDAOTest {

    private ImageDAO imageDAO;

    private List<Image> images;

    @Autowired
    public void setImageDAO(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }

    @Before
    public void init() {
        images = new ArrayList<>();
    }

    @After
    public void deleteAllImage() {
        for (Image image : images) {
            imageDAO.delete(image);
        }
    }

    @Test( expected = org.springframework.dao.DataIntegrityViolationException.class  )
    public void testCreateImageWithoutName() {
        Image image = new Image();
        imageDAO.save(image);
    }

    @Test
    public void testCreateAnImage() {
        Image image = new Image();
        image.setName("Test image");
        imageDAO.save(image);
        Iterable<Image> iterable = imageDAO.findAll();
        Iterator<Image> iterator = iterable.iterator();
        Image dbImage = iterator.next();
        assertThat(image.getId(), is(notNullValue()));
        assertThat(image.getName(), equalTo(dbImage.getName()));
        images.add(image);
    }

    @Test
    public void testFindByName() {
        Image image = new Image();
        image.setName("Test image");
        imageDAO.save(image);
        Image dbImage = imageDAO.findByName("Test image");
        assertThat(image.getName(), equalTo(dbImage.getName()));
        images.add(image);
    }


}
