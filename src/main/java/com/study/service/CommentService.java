package com.study.service;


import com.study.domain.CommentDTO;
import com.study.domain.CommentRequest;
import com.study.domain.CommentSaveRequest;

import java.util.List;

public interface CommentService {

	public void saveComment(CommentSaveRequest commentSaveRequest);

	public boolean deleteComment(Long id);

	public List<CommentDTO> getCommentList(CommentRequest commentRequest);

}
