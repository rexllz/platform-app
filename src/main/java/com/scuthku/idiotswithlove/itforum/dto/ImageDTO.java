package com.scuthku.idiotswithlove.itforum.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageDTO {
    private Integer userId;
    private MultipartFile picture;
}
