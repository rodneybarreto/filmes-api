package br.com.rodneybarreto.moviesapi.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageResponse<T> {

    private T type;
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalElements;

    public PageResponse() {
        this.type = null;
        this.content = Collections.emptyList();
        this.pageNumber = 0;
        this.pageSize = 0;
        this.totalPages = 0;
        this.totalElements = 0L;
    }

    public PageResponse(T type, List<T> content, int pageNumber, int pageSize, int totalPages, long totalElements) {
        this.type = type;
        this.content = content;
        this.pageNumber = Optional.of(pageNumber).orElse(0);
        this.pageSize = Optional.of(pageSize).orElse(0);
        this.totalPages = Optional.of(totalPages).orElse(0);
        this.totalElements = Optional.of(totalElements).orElse(0L);
    }

    public PageResponse(List<T> content, int pageNumber, int pageSize, int totalPages, long totalElements) {
        this.type = null;
        this.content = content;
        this.pageNumber = Optional.of(pageNumber).orElse(0);
        this.pageSize = Optional.of(pageSize).orElse(0);
        this.totalPages = Optional.of(totalPages).orElse(0);
        this.totalElements = Optional.of(totalElements).orElse(0L);
    }

    public T getType() {
        return type;
    }

    public void setType(T type) {
        this.type = type;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

}
