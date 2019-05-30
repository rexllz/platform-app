package com.scuthku.idiotswithlove.itforum.utils;

import com.scuthku.idiotswithlove.itforum.dto.ImageDTO;
import com.scuthku.idiotswithlove.itforum.enums.ControllerEnum;
import com.scuthku.idiotswithlove.itforum.handlers.exceptions.ControllerException;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;

public class ImageDownloadUtil {
    //private static String imageUriRoot=File.separatorChar+ "static" + File.separatorChar + "img" + File.separatorChar;
    private static String imageUriRoot=File.separatorChar+ "resources" + File.separatorChar;
    private static String imageFileRoot=File.separatorChar + "home"
            + File.separatorChar+ "arby"
            + File.separatorChar +"SCUT_FORUM"
            + File.separatorChar + "it-forum"
            + File.separatorChar + "src"
            + File.separatorChar + "main"
            + File.separatorChar + "resources"
            + File.separatorChar + "static"
            + File.separatorChar + "img"
            + File.separatorChar;


    public static String downloadPic(ImageDTO imageDTO) {
        String path = imageDTO.getUserId()
                + File.separatorChar +System.currentTimeMillis()
                + File.separatorChar + imageDTO.getPicture().getOriginalFilename();
        String uriPath = imageUriRoot + imageDTO.getUserId() + File.separatorChar + path;
        String filePath = imageFileRoot + imageDTO.getUserId() + File.separatorChar + path;


        File picFile = new File(filePath);
        if(!picFile.exists()) {
            if (!picFile.getParentFile().exists()) {
                picFile.getParentFile().mkdirs();
            }
        }
        try {
            //存储文件
            imageDTO.getPicture().transferTo(picFile);
        } catch (IOException e) {
            throw new ControllerException(ControllerEnum.FAIL_TO_SAVE_PICTURE);
        }
        return uriPath;
    }
}
