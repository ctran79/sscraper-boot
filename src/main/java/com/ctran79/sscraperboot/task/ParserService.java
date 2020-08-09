package com.ctran79.sscraperboot.task;

public interface ParserService {

    boolean isEnable() ;

    void scrape();

    Parser getSourceName();
}
