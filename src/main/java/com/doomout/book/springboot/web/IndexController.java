package com.doomout.book.springboot.web;

import com.doomout.book.springboot.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;

    @GetMapping("/")
    //서버 템플릿 엔진에서 사용할수 있는 객체를 저장
    //postsService.findAllDesc() 로 가져온 결과를 posts 로 index.mustache 에 전달합니다.
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

}
