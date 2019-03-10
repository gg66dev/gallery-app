package com.galleryapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.galleryapp.exception.NotFoundPageException;
import com.galleryapp.exception.NotFoundViewerException;
import com.galleryapp.exception.NotValidFileException;
import com.galleryapp.model.Comment;
import com.galleryapp.model.Image;
import com.galleryapp.model.PageView;
import com.galleryapp.repository.ImageDAO;
import com.galleryapp.repository.PageViewDAO;
import com.galleryapp.service.PageViewService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Handles requests for the application file upload requests
 */
@RestController
public class MainRestController {

    /**
     * image repository
     * */
    private ImageDAO imageDAO;

    /**
     * pageView repository
     */
    private PageViewDAO pageViewDAO;

    /**
     * service of pageviews
     */
    private PageViewService pageViewService;

    @Autowired
    public void setImageDAO(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }

    @Autowired
    public void setPageViewDAO(PageViewDAO pageViewDAO) {
        this.pageViewDAO = pageViewDAO;
    }

    @Autowired
    public void setPageViewService(PageViewService pageViewService) {
        this.pageViewService = pageViewService;
    }

    /**
     * Handler for Exception Not valid file
     * @param e the exception
     * @return an error response
     */
    @ExceptionHandler(value = NotValidFileException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<String> exception(NotValidFileException e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handler for Exception Not found page
     * @param e the exception
     * @return an error response
     */
    @ExceptionHandler(value = NotFoundPageException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<String> exception(NotFoundPageException e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handler for Exception Not found viewer
     * @param e the exception
     * @return an error response
     */
    @ExceptionHandler(value = NotFoundViewerException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<String> exception(NotFoundViewerException e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Upload single image
     * @param file file of the image
     * @return Image created
     */
    @PostMapping(value = "/uploadFile")
    public ResponseEntity<Image> uploadFileHandler(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new NotValidFileException();
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
        imageDAO.save(image);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    /**
     * get list of images
     * @return List of images
     */
    @GetMapping(value = "/images")
    public ResponseEntity<List<Image>> getImages() {
        Iterable<Image> it = imageDAO.findAll();
        Iterator<Image> iterator = it.iterator();
        List<Image> images = new ArrayList<>();
        while (iterator.hasNext()) {
            Image image = iterator.next();
            images.add(image);
        }
        return new ResponseEntity<>(images , HttpStatus.OK);
    }

    /**
     * add a comment
     * @param url url of image
     * @param messageComment the comment message
     * @param request servlet request
     * @return the created comment
     */
    @PostMapping(value = "/{url}/comment")
    public ResponseEntity<Comment> addComment (@PathVariable  String url,
                                               @RequestBody String messageComment,
                                               HttpServletRequest request)
            throws NotFoundPageException, NotFoundViewerException {
       String ip = request.getRemoteAddr();
       PageView pageView = pageViewService.findPageView(ip, url);
       Comment comment = new Comment();
       comment.setMessage(messageComment);
       comment.setCreatedDate(new Date());
       pageView.getComments().add(comment);
       pageViewDAO.save(pageView);
       return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    /**
     * like Image
     * @param url url of image
     * @param request servlet request
     * @return total number of likes of the image.
     */
    @PostMapping(value = "/{url}/like")
    public ResponseEntity<Integer> likeImage(@PathVariable String url,
                                             @RequestBody Boolean like,
                                             HttpServletRequest request)
            throws NotFoundPageException, NotFoundViewerException {
        Integer totalLikeImage;
        String ip = request.getRemoteAddr();
        PageView pageView = pageViewService.findPageView(ip, url);
        pageView.setLike(like);
        pageViewDAO.save(pageView);
        totalLikeImage = pageViewDAO.getTotalLikes(url);
        return new ResponseEntity<>(totalLikeImage, HttpStatus.OK);
    }


    /**
     * unlike Image
     * @param url url of image
     * @param request servlet request
     * @return total number of unlikes of the image.
     */
    @PostMapping(value = "/{url}/unlike")
    public ResponseEntity<Integer> unlikeImage(@PathVariable String url,
                                               @RequestBody Boolean unlike,
                                               HttpServletRequest request)
            throws NotFoundPageException, NotFoundViewerException {
        Integer totalUnlikeImage;
        String ip = request.getRemoteAddr();
        PageView pageView = pageViewService.findPageView(ip, url);
        pageView.setUnlike(unlike);
        pageViewDAO.save(pageView);
        totalUnlikeImage = pageViewDAO.getTotalUnlikes(url);
        return new ResponseEntity<>(totalUnlikeImage, HttpStatus.OK);
    }

}


