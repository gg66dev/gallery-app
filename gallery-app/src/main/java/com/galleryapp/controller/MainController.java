package com.galleryapp.controller;

import com.galleryapp.exception.NotFoundImageException;
import com.galleryapp.model.Image;
import com.galleryapp.model.Page;
import com.galleryapp.model.PageView;
import com.galleryapp.model.Viewer;
import com.galleryapp.service.PageViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class MainController {

    private PageViewService pageViewService;

    @Autowired
    public void setPageViewService(PageViewService pageViewService) {
        this.pageViewService = pageViewService;
    }

    /**
     * view to the list of images (home page)
     */
    @RequestMapping("/")
    public String home(HttpServletRequest request) throws NotFoundImageException {
        //register the viewer if doesnt exist
        String ip = request.getRemoteAddr();
        pageViewService.registerView(ip, "/");
        return "home";
    }

    /**
     * vire to the detail image page
     */
    @RequestMapping("/{imageHash:.+}")
    public String image(@PathVariable String imageHash,
                        HttpServletRequest request,
                        Model model) throws NotFoundImageException {
        if (imageHash.equals("favicon.ico")) {
            return "favicon.ico";
        }
        //register the viewer if doesnt exists
        String ip = request.getRemoteAddr();
        pageViewService.registerView(ip, imageHash);
        model.addAttribute("imageHash", imageHash);
        return "image";
    }
}