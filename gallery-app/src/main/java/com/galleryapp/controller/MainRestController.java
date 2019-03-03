package com.galleryapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import com.galleryapp.model.Image;
import com.galleryapp.repository.ImageDAO;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Handles requests for the application file upload requests
 */
@RestController
public class MainRestController {

    private ImageDAO imageDAO;

    @Autowired
    public void setImageDAO(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }

    /**
     * Upload single file using Spring Controller
     */
    @PostMapping(value = "/uploadFile")
    public ResponseEntity<String> uploadFileHandler(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("Not valid file");
        }
        byte[] bytes = file.getBytes();
        String name = file.getOriginalFilename();
        // Creating the directory to store file
        String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + "tmpFiles");
        if (!dir.exists())
            dir.mkdirs();
        //rename file name with sha-256
        Date uploadDate = new Date();
        String postfix = Long.toString(uploadDate.getTime());//Add timestap as postfix
        String[] arrFilename = name.split("\\.");
        String extension = "." + arrFilename[arrFilename.length - 1];
        name = name.replace(extension,"");
        name = DigestUtils.sha256Hex(name+postfix);
        name = name + extension;
        // Create the file on server
        File serverFile = new File(dir.getAbsolutePath()
                + File.separator + name);
        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();
        //save register in database
        Image image = new Image();
        image.setName(name);
        image.setUpdatedDate(uploadDate);
        imageDAO.saveImage(image);
        return new ResponseEntity<>("\""+name+"\"", HttpStatus.OK);
    }

}

