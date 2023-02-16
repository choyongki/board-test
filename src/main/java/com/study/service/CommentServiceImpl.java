package com.study.service;

import com.study.domain.CommentRequest;
import com.study.domain.CommentSaveRequest;
import com.study.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import com.study.domain.CommentDTO;

import javax.transaction.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

	private final CommentMapper commentMapper;

	@Transactional
	@Override
	public void saveComment(CommentSaveRequest commentSaveRequest) {
		commentMapper.saveComment(commentSaveRequest);
	}


	@Override
	public boolean deleteComment(Long idx) {
		int queryResult = 0;

		CommentDTO comment = commentMapper.selectCommentDetail(idx);

		if (comment != null && "N".equals(comment.getDeleteYn())) {
			queryResult = commentMapper.deleteComment(idx);
		}

		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<CommentDTO> getCommentList(CommentRequest commentRequest) {

		List<CommentDTO> commentList = commentMapper.selectCommentList(commentRequest);
		for (int i = 0; i < commentList.size(); i++) {
			commentRequest.setParentId(commentList.get(i).getId());
			commentList.get(i).setChildCommentList(commentMapper.selectCommentList(commentRequest));
		}

		log.info(commentList.toString());
		return commentList;
	}

}
