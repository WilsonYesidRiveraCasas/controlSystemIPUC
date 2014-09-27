/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipuc.web.helper;

import org.jogger.http.Request;

/**
 *
 * @author wilson-rivera
 */
public enum ResponseFormat {

    HTML("text/html; charset=UTF-8"),
    JSON("application/json"),
    XML("application/xml"),
    PDF ("application/pdf");

    private String contentType;

    private ResponseFormat(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public static ResponseFormat getResponseFormat(String accepts) {
        if (accepts != null && accepts.contains("json")) {
            return JSON;
        }
        return HTML;
    }

    public static ResponseFormat getResponseFormat(Request request) {
        String accept = request.getHeader("Accept");
        return ResponseFormat.getResponseFormat(accept);
    }
}