package br.com.rodneybarreto.filmesapi.adapters.inbound.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageRes<T> {

    private T t;
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalElements;

    public PageRes() {
        this.t = null;
        this.content = Collections.emptyList();
        this.pageNumber = 0;
        this.pageSize = 0;
        this.totalPages = 0;
        this.totalElements = 0L;
    }

    public PageRes(T t, List<T> content, int pageNumber, int pageSize, int totalPages, long totalElements) {
        this.t = t;
        this.content = content;
        this.pageNumber = Optional.of(pageNumber).orElse(0);
        this.pageSize = Optional.of(pageSize).orElse(0);
        this.totalPages = Optional.of(totalPages).orElse(0);
        this.totalElements = Optional.of(totalElements).orElse(0L);
    }

    public PageRes(List<T> content, int pageNumber, int pageSize, int totalPages, long totalElements) {
        this.t = null;
        this.content = content;
        this.pageNumber = Optional.of(pageNumber).orElse(0);
        this.pageSize = Optional.of(pageSize).orElse(0);
        this.totalPages = Optional.of(totalPages).orElse(0);
        this.totalElements = Optional.of(totalElements).orElse(0L);
    }

}
