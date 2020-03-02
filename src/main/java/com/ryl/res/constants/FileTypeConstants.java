package com.ryl.res.constants;

import java.util.Arrays;
import java.util.List;

/**
 * @author: ryl
 * @description:
 * @date: 2020-03-02 19:02:22
 */
public class FileTypeConstants {

    /**
     * 常用图片类型
     */
    public static final List<String> IMAGE_TYPE_LIST = Arrays.asList("JPG", "JPEG", "PNG", "GIF", "BMP", "WBMP", "TIF", "PIC");
    /**
     * 常用文档类型
     */
    public static final List<String> DOCUMENT_TYPE_LIST = Arrays.asList("TXT", "DOC", "HLP", "RTF", "HTML", "PDF");
    /**
     * 常用音频类型
     */
    public static final List<String> VOICE_TYPE_LIST = Arrays.asList("WAV", "AIF", "AU", "MP3", "RAM", "WMA", "MMF", "AMR", "AAC", "FLAC");
    /**
     * 常用视频类型
     */
    public static final List<String> VIDEO_TYPE_LIST = Arrays.asList("MP4", "RMVB", "RM", "MPEG", "AVI", "MOV", "ASF", "WMV", "NAVI", "3GP", "MKV", "FLV", "F4V");
    /**
     * 常用压缩类型
     */
    public static final List<String> COMPRESS_TYPE_LIST = Arrays.asList("RAR", "ZIP", "7Z");

    /**
     * 图片类型
     */
    public static final String IMAGE_TYPE = "0";
    /**
     * 文档类型
     */
    public static final String DOCUMENT_TYPE = "1";
    /**
     * 视频类型
     */
    public static final String VOICE_TYPE = "2";
    /**
     * 音频类型
     */
    public static final String VIDEO_TYPE = "3";
    /**
     * 压缩类型
     */
    public static final String COMPRESS_TYPE = "4";

    /**
     * 其他类型
     */
    public static final String OTHER_TYPE = "5";
}
