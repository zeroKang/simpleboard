package org.zerock.simpleboard.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.simpleboard.dto.requestpage.SearchRequestDTO;
import org.zerock.simpleboard.dto.list.ListBoardResponseDTO;
import org.zerock.simpleboard.dto.SimpleBoardDTO;
import org.zerock.simpleboard.service.SimpleBoardService;

@Controller
@RequestMapping("/sboard")
@Log4j2
@AllArgsConstructor
public class SimpleBoardController {

    private SimpleBoardService simpleBoardService;

    @GetMapping("/list")
    public void list(@ModelAttribute("requestDTO") SearchRequestDTO searchRequestDTO, Model model){
        log.info("list..............");

        ListBoardResponseDTO responseDTO = simpleBoardService.listSearchPage(searchRequestDTO);

        model.addAttribute("data", responseDTO);
    }

    @GetMapping("/register")
    public void registerGET(){
        log.info("get register....");
    }

    @PostMapping("/register")
    public String registerPOST(SimpleBoardDTO simpleBoardDTO, RedirectAttributes redirectAttributes){
        log.info("post register....");

        log.info(simpleBoardDTO);

        simpleBoardService.register(simpleBoardDTO);

        redirectAttributes.addFlashAttribute("result", "success");

        return "redirect:/sboard/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(Long bno, @ModelAttribute("requestDTO") SearchRequestDTO searchRequestDTO, Model model){

        log.info("get read...." + bno);

        SimpleBoardDTO simpleBoardDTO = simpleBoardService.get(bno);

        log.info("simpleBoardDTO: " + simpleBoardDTO);

        model.addAttribute("boardDTO", simpleBoardDTO);

    }

    @PostMapping("/modify")
    public String modifyPOST(SimpleBoardDTO simpleBoardDTO,
                             SearchRequestDTO searchRequestDTO,
                             RedirectAttributes redirectAttributes){

        log.info("modify post....." + simpleBoardDTO);
        log.info("requestDTO: " , searchRequestDTO);
        simpleBoardService.modify(simpleBoardDTO);
        redirectAttributes.addFlashAttribute("result", "success");

        return "redirect:" + searchRequestDTO.getLink("/sboard/read","bno",simpleBoardDTO.getBno());
    }

    @PostMapping("/remove")
    public String removePOST(Long bno, RedirectAttributes redirectAttributes){

        log.info("remove post ..." + bno);

        simpleBoardService.remove(bno);

        redirectAttributes.addFlashAttribute("result", "success");
        return "redirect:/sboard/list";
    }


}
