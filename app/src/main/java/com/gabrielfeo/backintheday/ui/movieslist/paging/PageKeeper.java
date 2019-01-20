package com.gabrielfeo.backintheday.ui.movieslist.paging;

public final class PageKeeper {

    private int page = 1;

    public int getCurrent() {
        return page;
    }

    public void inc() {
        page++;
    }

    public void reset() {
        page = 1;
    }

}
