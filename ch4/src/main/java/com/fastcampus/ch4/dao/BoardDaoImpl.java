package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.SearchCondition;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardDaoImpl implements BoardDao {
    @Autowired
    SqlSession session;

    String namespace="com.fastcampus.ch4.dao.BoardMapper.";


    public int insert(BoardDto dto) throws Exception {
        return session.insert(namespace+"insert", dto);
    }


    public BoardDto select(Integer bno) throws Exception {
        return session.selectOne(namespace+"select", bno);
    }

    public List<BoardDto> selectPage(Map map) throws Exception {
        return session.selectList(namespace+"selectPage", map);
    }

    public List<BoardDto> selectAll() throws Exception {
        return session.selectList(namespace+"selectAll");
    }

    public int count() throws Exception {
        return session.selectOne(namespace+"count");
    }



    public List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception {
        return session.selectList(namespace+"searchSelectPage", sc);
    }

    public int searchResultCnt(SearchCondition sc) throws Exception {
        return session.selectOne(namespace+"searchResultCnt", sc);
    }



/*    @Override
    public int updateCommentCnt(Integer bno, int cnt) { // boardDao.updateCommentCnt(2, -1);
        Map map = new HashMap();
        map.put("bno", bno); // bno = 2
        map.put("cnt", cnt); // cnt = -1
        return session.update(namespace+"updateCommentCnt", map);
    }*/


    @Override
    public int updateCommentCnt(Integer bno, int cnt) { // boardDao.updateCommentCnt(2, 1);
        Map map = new HashMap();
        map.put("bno", bno); // bno = 2
        map.put("cnt", cnt); // cnt = 1
        return session.update(namespace+"updateCommentCnt", map);
    }



    public int update(BoardDto dto) throws Exception {
        return session.update(namespace+"update", dto);
    }


    public int increaseViewCnt(Integer bno) throws Exception {
        return session.update(namespace+"increaseViewCnt", bno);
    }




    public int delete(Integer bno, String writer) throws Exception {
        Map map = new HashMap();
        map.put("bno", bno);
        map.put("writer", writer);
        return session.delete(namespace+"delete", map);
    }

    @Override
    public int deleteAll() {
        return session.delete(namespace+"deleteAll");
    }


}
