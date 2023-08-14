package com.boot.board230809.controller;

import com.boot.board230809.model.Board;
import com.boot.board230809.repository.BoardRepository;
import com.boot.board230809.validator.BoardValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model){
//        repository이용 -> select를 하지 않아도 조회 가능
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/form")
//    @RequestParam(required = false) : 넘어오는 값이 필수값이 아니라는 뜻
    public String form(Model model, @RequestParam(required = false) Long id){
        log.info("@# id =>"+id);
        if (id == null){
            model.addAttribute("board", new Board());
        }else {
            Optional<Board> board = boardRepository.findById(id);
            model.addAttribute("board", board);
        }
        return "board/form";
    }

    /*@PostMapping("/form")
    public String greetingSubmit(@ModelAttribute Board board, Model model) {
        boardRepository.save(board);
        return "redirect:/board/list";
    }*/

    @PostMapping("/form")
//    public String greetingSubmit(@ModelAttribute Board board, Model model) {
    public String form(@Valid Board board, BindingResult bindingResult) {
        boardValidator.validate(board, bindingResult);

        if (bindingResult.hasErrors()) {
            return "board/form";
        }
        boardRepository.save(board);
        return "redirect:/board/list";
    }
}
