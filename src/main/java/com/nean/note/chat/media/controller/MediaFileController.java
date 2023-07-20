package com.nean.note.chat.media.controller;

import com.nean.note.chat.common.RestResponse;
import com.nean.note.chat.media.model.dto.UploadFileParamsDto;
import com.nean.note.chat.media.service.MediaFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/media")
public class MediaFileController {

    @Resource
    private MediaFileService mediaFileService;

    @RequestMapping(value = "/upload/file",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public RestResponse<String> upload(@RequestPart("file") MultipartFile file,
                                       @RequestParam(value = "filename") String filename){
        if(filename.isEmpty()){
            return RestResponse.success("error filename is null");
        }
        Long userID = 1680218136505892865L;
        String contentType = file.getContentType();
        String type;
        if(contentType != null && contentType.contains("image")){
            type = "10001";
        }else if(contentType != null && contentType.contains("video")){
            type = "10002";
        }else {
            type = "10003";
        }
        UploadFileParamsDto uploadFileParamsDto = UploadFileParamsDto.builder()
                .filename(file.getOriginalFilename())
                .fileType(type)
                .fileSize(file.getSize())
                .build();
        String url = "";
        try {
            url = mediaFileService.uploadFile(file.getBytes(),userID,uploadFileParamsDto,filename);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return RestResponse.success(url);
    }
}
