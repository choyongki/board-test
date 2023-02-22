package com.study.service;

import com.study.domain.PostDTO;
import com.study.file.UploadFile;
import com.study.mapper.PostMapper;
import com.study.domain.PostRequest;
import com.study.domain.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;

    /**
     * 게시글 저장
     * @param params - 게시글 정보
     * @return Generated PK
     */
    @Transactional
    public Long savePost(final PostDTO params) {
        log.info("PostService :: savePost");

        postMapper.save(params);
        return params.getId();
    }

    /**
     * 게시글 상세정보 조회
     * @param id - PK
     * @return 게시글 상세정보
     */
    public PostDTO findById(final Long id) {
        return postMapper.findById(id);
    }

    /**
     * 게시글 수정
     * @param params - 게시글 정보
     * @return PK
     */
    @Transactional
    public Long updatePost(final PostRequest params) {
        postMapper.update(params);
        return params.getId();
    }

    /**
     * 게시글 삭제
     * @param id - PK
     * @return PK
     */
    public void deletePost(Long id) {
        postMapper.deleteById(id);
    }

    /**
     * 게시글 리스트 조회
     * @return 게시글 리스트
     */
    public List<PostResponse> findAllPost() {
        return postMapper.findAll();
    }

    public List<PostDTO> findByBoardId(Long id){
        return postMapper.findByBoardId(id);
    }

    public List<PostDTO> findByBoardIdAndNotice(Long id){
        return postMapper.findByBoardIdAndNotice(id);
    }


}