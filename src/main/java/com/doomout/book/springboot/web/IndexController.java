package com.doomout.book.springboot.web;

import com.doomout.book.springboot.config.auth.LoginUser;
import com.doomout.book.springboot.config.auth.dto.SessionUser;
import com.doomout.book.springboot.service.PostsService;
import com.doomout.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    //서버 템플릿 엔진에서 사용할수 있는 객체를 저장
    //postsService.findAllDesc() 로 가져온 결과를 posts 로 index.mustache 에 전달합니다.
    // 기존 httpSession.getAttribute("user") 로 가져오던 세션 정보 값을 개선
    // @LoginUser 만 사용하면 세션 정보를 가져올 수 있음
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        
        //로그인 성공시 세션에서 SessionUser 를 저장하도록 구성
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");
        
        //세션에 저장된 값이 있을 때만 model 에 userNmae 으로 등록
        //세션에 저장된 값이 없으면 model엔 아무런 값이 없는 상태이니 로그인 버튼이 보이게 됨
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
