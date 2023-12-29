package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.CommentDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    private SqlSession session;

    private static String namespace = "com.fastcampus.ch4.dao.CommentMapper."; // Mapper.xml파일의 namespace와 일치해야 함. / 뒤에 점(.)을 붙여줌.

    @Override
    public int deleteAll(Integer bno) throws Exception {
        return session.delete(namespace+"deleteAll", bno);
    }

    @Override
    public int count(Integer bno) throws Exception {
        return session.selectOne(namespace+"count", bno);
    }

    @Override
    public int delete(Integer cno, String commenter) throws Exception { // commentDao.delete(10, asdf2);
        Map map = new HashMap();
        map.put("cno", cno); // cno = 10
        map.put("commenter", commenter);  // commenter = asdf
        return session.delete(namespace+"delete", map);
    }

    @Override
    public int insert(CommentDto dto) throws Exception { // commentDao.insert(dto);
        return session.insert(namespace+"insert", dto);
    }

    @Override
    public List<CommentDto> selectAll(Integer bno) throws Exception { // commentDao.selectAll(1);
        return session.selectList(namespace+"selectAll", bno);
    }

    @Override
    public CommentDto select(Integer cno) throws Exception {
        return session.selectOne(namespace + "select", cno);
    }

    @Override
    public int update(CommentDto dto) throws Exception { // commentDao.update(dto)
        return session.update(namespace + "update", dto);
    }

}
