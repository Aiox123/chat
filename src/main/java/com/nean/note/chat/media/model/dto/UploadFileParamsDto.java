package com.nean.note.chat.media.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileParamsDto {

    /*
     *  文件名称
     * */
    private String filename;

    /*
     *  文件类型
     * */
    private String fileType;

    /*
     *  文件大小
     * */
    private Long fileSize;

    /*
     *  备注
     * */
    private String remark;
}
