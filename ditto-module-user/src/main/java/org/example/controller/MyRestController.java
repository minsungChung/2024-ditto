package org.example.controller;

import groovy.util.logging.Slf4j;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.domain.Member;
import org.example.dto.request.MemberJoinRequest;
import org.example.dto.request.MemberPasswordRequest;
import org.example.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MyRestController {

    private static final Logger log = LoggerFactory.getLogger(MyRestController.class);
    private final MyService myService;
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/member/{id}")
    public Member getMember(@PathVariable("id") String no){
        return myService.getMember(no);
    }

    @PostMapping("/member")
    public int createMember(@Valid @RequestBody MemberJoinRequest memberJoinRequest){
        log.info("여기까지 오케이~~");
        return myService.saveMember(memberJoinRequest);
    }

    @PatchMapping("/mypage/{memberId}/password")
    public int updateMemberPassword(@RequestHeader("memberId") Long myId, @PathVariable("memberId") Long memberId, @Valid @RequestBody MemberPasswordRequest memberPasswordRequest){
        return myService.updateMemberPassword(myId, memberId, memberPasswordRequest);
    }

    @DeleteMapping("/member/{memberId}")
    public int deleteMember(@RequestHeader("memberId") Long myId, @PathVariable("memberId") Long memberId){
        return myService.deleteMember(myId, memberId);
    }

    @GetMapping("/login-success")
    public void loginSuccess(@RequestParam("token") String token, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        // 메인 화면으로 포워딩
        request.getRequestDispatcher("/members").forward(request, response);
    }


}
