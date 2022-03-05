package com.javasql.custom.query.dto.response;

import java.util.List;

public class BookPageRes {

    private List<BookResponse> data;
    private PageRes page;

    public BookPageRes(List<BookResponse> data, PageRes page) {
        this.data = data;
        this.page = page;
    }

    public List<BookResponse> getData() {
        return data;
    }

    public void setData(List<BookResponse> data) {
        this.data = data;
    }

    public PageRes getPage() {
        return page;
    }

    public void setPage(PageRes page) {
        this.page = page;
    }
}
