package org.zerock.simpleboard.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.simpleboard.dto.ListRequestDTO;
import org.zerock.simpleboard.dto.ListResponseDTO;
import org.zerock.simpleboard.service.SimpleBoardService;

@Controller
@RequestMapping("/sboard")
@Log4j2
@AllArgsConstructor
public class SimpleBoardController {

    private SimpleBoardService simpleBoardService;

    @GetMapping("/list")
    public void list(@ModelAttribute("requestDTO") ListRequestDTO listRequestDTO, Model model){
        log.info("list..............");

        ListResponseDTO responseDTO = simpleBoardService.listSearchPage(listRequestDTO);

        model.addAttribute("data", responseDTO);
    }

}
