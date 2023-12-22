package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TxService {
    @Autowired A1Dao a1Dao;
    @Autowired B1Dao b1Dao;

    public void insertWithoutTx() throws Exception {
        a1Dao.insert(1, 100); // 성공
        a1Dao.insert(1, 200); // 실패

        //  a1Dao.insert(1, 100); -> com.mysql.cj.jdbc.ConnectionImpl@247667dd 사용
        //  a1Dao.insert(1, 200); -> com.mysql.cj.jdbc.ConnectionImpl@13cd7ea5 사용

        // select하면 (1, 100)이 조회됨.
    }


    @Transactional(rollbackFor=Exception.class)  // 자동으로 commit()과 rollback()을 주입해줌.
    public void insertWithTx() throws Exception {
        a1Dao.insert(1, 100); // 성공
        a1Dao.insert(1, 200); // 실패

        // a1Dao.insert(1, 100); -> com.mysql.cj.jdbc.ConnectionImpl@42e22a53 사용
        // a1Dao.insert(1, 200); -> com.mysql.cj.jdbc.ConnectionImpl@42e22a53 사용

        // select해도 아무것도 조회되지 않음.
    }




////////////////////////////////////




    /*
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
    public void insertA1WithTx() throws Exception {
        a1Dao.insert(1, 100); // 성공
        insertB1WithTx();
        a1Dao.insert(2, 400); // 성공
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
    public void insertB1WithTx() throws Exception {
        b1Dao.insert(1, 200); // 성공
        b1Dao.insert(1, 300); // 실패
    }

    // a1Dao.insert(1, 100) ->  com.mysql.cj.jdbc.ConnectionImpl@39109136 사용
    // b1Dao.insert(1, 200); -> com.mysql.cj.jdbc.ConnectionImpl@39109136 사용
    // b1Dao.insert(1, 300); -> com.mysql.cj.jdbc.ConnectionImpl@39109136 사용
    // a1Dao.insert(2, 400) ->  com.mysql.cj.jdbc.ConnectionImpl@39109136 사용

    // a1과 b1 모두 select해도 아무것도 조회되지 않음.
    */


    //////////////////////


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
    public void insertA1WithTx() throws Exception {
        a1Dao.insert(1, 100); // 성공
        insertB1WithTx();
        a1Dao.insert(2, 400); // 성공
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
    public void insertB1WithTx() throws Exception {
        b1Dao.insert(1, 200); // 성공
        b1Dao.insert(1, 300); // 실패
    }

    // a1Dao.insert(1, 100) ->  com.mysql.cj.jdbc.ConnectionImpl@39109136 사용
    // b1Dao.insert(1, 200); -> com.mysql.cj.jdbc.ConnectionImpl@67403656 사용
    // b1Dao.insert(1, 300); -> com.mysql.cj.jdbc.ConnectionImpl@67403656 사용
    // a1Dao.insert(2, 400) ->  com.mysql.cj.jdbc.ConnectionImpl@39109136 사용

    // a1을 select하면 (1, 100)과 (2, 400)가 조회됨.  /  b1을 select하면 아무것도 조회되지 않음.
}
