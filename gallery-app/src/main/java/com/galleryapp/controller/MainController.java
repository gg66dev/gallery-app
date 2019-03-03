package com.galleryapp.controller;

import com.galleryapp.model.Image;
import com.galleryapp.repository.ImageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {


    /**
     * return view to the list of images
     */
    @RequestMapping("/")
    public String home() {
        return "home";
    }


}