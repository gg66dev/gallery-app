package com.galleryapp.repository;

import com.galleryapp.model.Image;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:gallery-app-servlet.xml"})
public class ImageDAOTest {

    private ImageDAO imageDAO;

    @Autowired
    public void setImageDAO(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }

    @Before
    public void deleteAllImage() {
        imageDAO.deleteAll();
    }


    @Test
    public void testCreateAnImage() {
        Image image = new Image();
        image.setName("Test image");
        imageDAO.save(image);

        Iterable<Image> iterable = imageDAO.findAll();
        Iterator<Image> iterator = iterable.iterator();
        Image imageDb = iterator.next();

        assertThat(image.getName(), equalTo(imageDb.getName()));
    }


}
