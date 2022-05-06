package com.doomout.book.springboot.service;

import com.doomout.book.springboot.domain.posts.Posts;
import com.doomout.book.springboot.domain.posts.PostsRepository;
import com.doomout.book.springboot.web.dto.PostsListResponseDto;
import com.doomout.book.springboot.web.dto.PostsResponseDto;
import com.doomout.book.springboot.web.dto.PostsSaveRequestDto;
import com.doomout.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        //JpaRepository 에서 이미 delete 메소드를 지원하고 있다.
        //엔티티를 파라미터롤 삭제 할 수 있고, deleteById 메소드를 이용하면 id로 삭제할 수도 있다.
        //존재하는 Posts 인지 확인을 위해 엔티티 조회 후 그대로 삭제 한다.
        postsRepository.delete(posts);
    }


    @Transactional(readOnly = true) //트랜젝션 범위는 유지, 조회기능은 남겨두어 조회 속도 개선
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
