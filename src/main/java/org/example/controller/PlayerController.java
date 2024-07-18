package org.example.controller;

import org.example.model.Player;
import org.example.service.IPlayerService;
import org.example.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private IPlayerService playerService;

    @GetMapping("")
    public String showList(Model model) {
        List<Player> players = playerService.findAll();
        model.addAttribute("players", players);
        return "/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        Player player = playerService.findById(id);
        if (player == null) {
            return "redirect:/player";
        }
        model.addAttribute("player", player);
        return "/detail";
    }

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        model.addAttribute("player", new Player());
        return "/create";
    }

    @PostMapping("/create")
    public String save(@ModelAttribute("player") Player player, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasFieldErrors()) {
            System.out.println("error");
            return "/create";
        }
        redirectAttributes.addFlashAttribute("message", "Thêm thành công");
        playerService.save(player);
        System.out.println("thành công");
        return "redirect:/player";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        Player player = playerService.findById(id);
        if (player == null) {
            return "redirect:/list";
        }
        model.addAttribute("player", player);
        return "/edit";
    }

    @PostMapping("/edit")
    public String saveEdit(@ModelAttribute Player player) {
        playerService.edit(player);
        return "redirect:/player";
    }
    @GetMapping("/search")
    public String search(@RequestParam("search") String search, Model model){
       List<Player> players = playerService.findByName(search);
        model.addAttribute("players",players);
        return "/list";
    }

}


