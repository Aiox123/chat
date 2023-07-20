package com.nean.note.chat.media.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import com.nean.note.chat.media.mapper.MediaFileMapper;
import com.nean.note.chat.media.model.dto.UploadFileParamsDto;
import com.nean.note.chat.media.model.pojo.MediaFile;
import com.nean.note.chat.media.service.MediaFileService;
import com.nean.note.chat.utils.FormatUtils;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;


@Slf4j
@Service
public class MediaFileServiceImpl extends ServiceImpl<MediaFileMapper, MediaFile>
        implements MediaFileService {

    @Resource
    private MediaFileMapper mediaFileMapper;

    @Resource
    private MinioClient minioClient;

    @Value("${file.url.prefix}")
    private String fileUrlPrefix;

    @Value("${minio.bucket}")
    private String minioBucket;

    @Override
    public String uploadFile(byte[] bytes, Long userID, UploadFileParamsDto uploadFileParamsDto, String filename) {
        try{
            String fileMd5 = DigestUtils.md5Hex(bytes);
            FormatUtils dataFormat = new FormatUtils();
            String folder = dataFormat.getFileFolder(true,true,true);
            filename = userID + "/" + folder + filename;
            log.info("上传人：{}，上传文件名称：{}", userID, filename);

            addMediaFileToMinio(bytes, filename, minioBucket);

            // generate MediaFile only if it does not exist
            MediaFile mediaFile = generateMediaFile(userID, uploadFileParamsDto, fileMd5, filename);
            if(mediaFile == null){
                return "上传失败";
            }

            return mediaFile.getUrl();
        } catch (Exception e) {
            log.error("文件上传失败: {}", e.getMessage());
            throw new RuntimeException("文件上传失败", e);
        }
    }

    @Transactional
    public MediaFile generateMediaFile(Long userID, UploadFileParamsDto uploadFileParamsDto, String fileId, String filePath){
        MediaFile mediaFile = mediaFileMapper.selectById(fileId);
        if(mediaFile == null){
            mediaFile = MediaFile.builder()
                    .id(fileId)
                    .userId(userID)
                    .filename(uploadFileParamsDto.getFilename())
                    .fileType(uploadFileParamsDto.getFileType())
                    .bucket(minioBucket)
                    .filePath(filePath)
                    .url(fileUrlPrefix + minioBucket + "/" + filePath)
                    .createDate(LocalDateTime.now())
                    .status(1)
                    .remark("暂无")
                    .fileSize(uploadFileParamsDto.getFileSize())
                    .build();
            mediaFileMapper.insert(mediaFile);
        }
        return mediaFile;
    }

    private void addMediaFileToMinio(byte[] bytes, String objectName, String bucket){
        // 资源的媒体类型
        String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        // 获取 objectName 的扩展名
        if(objectName.contains(".")){
            String extension = objectName.substring(objectName.lastIndexOf("."));
            ContentInfo contentInfo = ContentInfoUtil.findExtensionMatch(extension);
            if(contentInfo != null){
                contentType = contentInfo.getMimeType();
            }
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket(bucket)
                .object(objectName)
                // InputStream stream, long objectSize 对象大小, long partSize 分片大小
                .stream(byteArrayInputStream,byteArrayInputStream.available(),-1)
                .contentType(contentType)
                .build();
        // 上传到 minio
        try{
            minioClient.putObject(putObjectArgs);
        }catch (Exception e){
            e.printStackTrace();
            log.error("文件上传到 minio 出错: {}",e.getMessage());
        }
    }
}
