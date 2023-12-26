package com.fastcampus.ch4.domain;

public class PageHandler {
    int totalCnt;
    int pageSize;
    int naviSize = 10;
    int totalPage;
    int page;
    int beginPage;
    int endPage;
    boolean showPrev;
    boolean showNext;

    public PageHandler(int totalCnt, int page) {
        this(totalCnt, page, 10);
    }

    public PageHandler(int totalCnt, int page, int pageSize) {
        this.totalCnt = totalCnt;
        this.page = page;
        this.pageSize = pageSize;

    totalPage = (int)Math.ceil(totalCnt /pageSize);
    beginPage =page/naviSize * naviSize+1;
    endPage =Math.min(beginPage +naviSize-1, totalPage);
    showPrev = beginPage != 1;
    showNext = endPage != totalPage;
    }

    void print() {
        System.out.println(page);
        System.out.print(showPrev ? "[PREV]" : "");
        for (int i = beginPage; i <= endPage; i ++) {
            System.out.print(i+"");
        }
        System.out.println(showNext ? "[NEXT]" : "");
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "totalCnt=" + totalCnt +
                ", pageSize=" + pageSize +
                ", naviSize=" + naviSize +
                ", totalPage=" + totalPage +
                ", page=" + page +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}
