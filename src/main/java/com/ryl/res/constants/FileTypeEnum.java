package com.ryl.res.constants;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: ryl
 * @description:
 * @date: 2020-03-02 19:22:24
 */
@AllArgsConstructor
public enum FileTypeEnum {

    DOCUMENT_TYPE_LIST(0, Arrays.asList("TXT", "DOC", "HLP", "RTF", "HTML", "PDF")),
    IMAGE_TYPE_LIST(1, Arrays.asList("JPG", "JPEG", "PNG", "GIF", "BMP", "WBMP", "TIF", "PIC")),
    VOICE_TYPE_LIST(2, Arrays.asList("WAV", "AIF", "AU", "MP3", "RAM", "WMA", "MMF", "AMR", "AAC", "FLAC")),
    VIDEO_TYPE_LIST(3, Arrays.asList("MP4", "RMVB", "RM", "MPEG", "AVI", "MOV", "ASF", "WMV", "NAVI", "3GP", "MKV", "FLV", "F4V")),
    COMPRESS_TYPE_LIST(4, Arrays.asList("RAR", "ZIP", "7Z")),
    OTHER_TYPE_LIST(5, new ArrayList<>());

    private int type;

    private List<String> typeList;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }
}
