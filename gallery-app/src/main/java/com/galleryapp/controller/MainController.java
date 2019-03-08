package com.galleryapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {


    /**
     * return view to the list of images
     */
    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/{imageHash:.+}")
    public String image(@PathVariable String imageHash, Model model) {
       model.addAttribute("permalink", imageHash);
       return "image";
    }
}