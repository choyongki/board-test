package study.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/sign")
@RequiredArgsConstructor
@Controller
public class SignController {

    @GetMapping("/sign-in")
    public String signIn(){

        return "sign/sign-in";
    }

    @GetMapping("/sign-up")
    public String signUp(){

        return "sign/sign-up";
    }



}
