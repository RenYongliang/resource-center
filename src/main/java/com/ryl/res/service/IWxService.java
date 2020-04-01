package com.ryl.res.service;

import java.io.UnsupportedEncodingException;

/**
 * @author: ryl
 * @description:
 * @date: 2020-03-30 13:54:17
 */
public interface IWxService {
    String getQRcodeUrl() throws UnsupportedEncodingException;
}
