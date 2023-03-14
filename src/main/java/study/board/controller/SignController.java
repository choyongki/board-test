package study.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.board.controller.request.signUpRequest;
import study.board.controller.request.signInRequest;
import study.board.domain.dto.MemberDTO;
import study.board.service.SignService;

@Slf4j
@RequestMapping("/sign")
@RequiredArgsConstructor
@Controller
public class SignController {
    private final BCryptPasswordEncoder encoder;
    private final SignService signService;

    /**
     * 로그인 페이지
     * @return
     */
    @GetMapping("/sign-in/view")
    public String signInForm(){

        return "sign/sign-in";
    }

    /**
     * 회원가입 폼 페이지
     * @return
     */
    @GetMapping("/sign-up/form")
    public String signUpForm(){

        return "sign/sign-up";
    }

    /**
     * 회원가입 처리
     * @param signUpRequest
     * @return
     */
    @PostMapping("/sign-up")
    public String signUp(signUpRequest signUpRequest){
        log.info("회원가입 진행 : {}", signUpRequest.toString());
        signService.signUp(signUpRequest.toMemberDTO(encoder.encode(signUpRequest.getPassword())));

        return "redirect:/board";
    }

    @PostMapping("/sign-in")
    public String signIn(signInRequest signInRequest){
        log.info("로그인 진행 : {}", signInRequest.toString());
        signService.signIn(signInRequest.toMemberDTO(encoder.encode(signInRequest.getPassword())));

        return "redirect:/board";
    }



}
