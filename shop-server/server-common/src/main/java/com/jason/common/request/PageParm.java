package com.jason.common.request;

import lombok.Data;

@Data
public class PageParm {
    private Integer currentPage;
    private Integer pageSize;
    private String nickName;
}
