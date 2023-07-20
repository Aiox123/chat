package com.nean.note.chat.media.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nean.note.chat.media.model.dto.UploadFileParamsDto;
import com.nean.note.chat.media.model.pojo.MediaFile;

public interface MediaFileService extends IService<MediaFile> {

    String uploadFile(byte[] bytes, Long userID, UploadFileParamsDto uploadFileParamsDto, String filename);
}
