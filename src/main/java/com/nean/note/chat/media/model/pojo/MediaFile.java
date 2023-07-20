package com.nean.note.chat.media.model.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaFile implements Serializable {
    /**
     * 文件id,md5值
     */
    @TableId
    private String id;

    /**
     * 上传用户ID
     */
    private Long userId;

    /**
     * 文件名称
     */
    private String filename;

    /**
     * 文件类型（图片、文档，视频）
     */
    private String fileType;

    /**
     * 存储目录
     */
    private String bucket;

    /**
     * 存储路径
     */
    private String filePath;

    /**
     * 媒资文件访问地址
     */
    private String url;

    /**
     * 上传时间
     */
    private LocalDateTime createDate;

    /**
     * 修改时间
     */
    private LocalDateTime changeDate;

    /**
     * 状态,1:正常，0:不展示
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 文件大小
     */
    private Long fileSize;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
