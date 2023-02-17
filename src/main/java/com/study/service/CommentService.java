package com.study.service;


import com.study.domain.CommentDTO;
import com.study.domain.CommentDeleteRequest;
import com.study.domain.CommentRequest;
import com.study.domain.CommentSaveRequest;

import java.util.List;

public interface CommentService {

	void saveComment(CommentSaveRequest commentSaveRequest);

	void deleteComment(CommentDeleteRequest commentDeleteRequest);

	List<CommentDTO> getCommentList(CommentRequest commentRequest);

}
