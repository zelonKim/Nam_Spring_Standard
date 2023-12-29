package com.fastcampus.ch4.service;

import com.fastcampus.ch4.dao.BoardDao;
import com.fastcampus.ch4.dao.CommentDao;
import com.fastcampus.ch4.domain.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    BoardDao boardDao;

    @Autowired
    CommentDao commentDao;


    @Override
    public int getCount(Integer bno) throws Exception {
        return commentDao.count(bno);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(Integer cno, Integer bno, String commenter) throws Exception {
        int rowCnt = boardDao.updateCommentCnt(bno, -1);  // boardDao.updateCommentCnt(2, -1);
        rowCnt= commentDao.delete(cno, commenter);  // commentDao.delete(10, asdf);
        return rowCnt;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int write(CommentDto commentDto) throws Exception {
        boardDao.updateCommentCnt(commentDto.getBno(), 1);  //  boardDao.updateCommentCnt(2, 1);
        return commentDao.insert(commentDto);  // commentDao.insert(dto);
    }


    @Override
    public List<CommentDto> getList(Integer bno) throws Exception {
        return commentDao.selectAll(bno);  // commentDao.selectAll(1);
        // throw new Exception("test");
    }

    @Override
    public CommentDto read(Integer cno) throws Exception {
        return commentDao.select(cno);
    }

    @Override
    public int modify(CommentDto commentDto) throws Exception {
        return commentDao.update(commentDto); // commentDao.update(dto)
    }

}
