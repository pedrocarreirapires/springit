package com.vega.springit.controller;

import com.vega.springit.domain.Comment;
import com.vega.springit.domain.Link;
import com.vega.springit.domain.Vote;
import com.vega.springit.repository.CommentRepository;
import com.vega.springit.repository.LinkRepository;
import com.vega.springit.service.CommentService;
import com.vega.springit.service.LinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class LinkController {

    private static final Logger logger = LoggerFactory.getLogger(LinkController.class);

    private LinkService linkService;
    private CommentService commentService;


    public LinkController(LinkService linkService, CommentService commentService) {
        this.linkService = linkService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("links", linkService.findAll());
        return "link/list";
    }

    @GetMapping("/link/{id}")
    public String read(@PathVariable Long id, Model model){

        Optional<Link> link = linkService.findById(id);
        if(link.isPresent() ){
            Link currentLink = link.get();
            Comment comment = new Comment();

            comment.setLink(currentLink);

            model.addAttribute("comment", comment);
            model.addAttribute("link", currentLink);
            model.addAttribute("success", model.containsAttribute("success"));

            return "link/view";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/link/submit")
    public String newLinkForm(Model model){
        model.addAttribute("link", new Link());
        return "link/submit";
    }

    @PostMapping("/link/submit")
    public String createLink(@Valid Link link, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            logger.info("Validation errors were found while submitting a new link.");
            model.addAttribute("link", link);
            return "link/submit";
        } else {
            //Save our link
            linkService.save(link);
            logger.info("New link was saved succesfully.");
            redirectAttributes
                    .addAttribute("id", link.getId())
                    .addFlashAttribute("success", true);
            return "redirect:/link/" + link.getId();
        }
    }

    @PostMapping("/link/comments")
    public String addComment(@Valid Comment comment, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            logger.info("There was a problem adding a new comment.");
        } else {
            commentService.save(comment);
            logger.info("New comment was saved succesfully.");
        }
        return "redirect:/link/" + comment.getLink().getId();
    }

}
