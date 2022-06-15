package vn.edu.hcmuaf.fit.dto.pagination;

public class PageParam {
    private int totalPage;
    private int currentPage;

    public PageParam() {
    }

    public PageParam(int totalPage, int currentPage) {
        this.totalPage = totalPage;
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
