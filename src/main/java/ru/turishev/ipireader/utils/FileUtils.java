package ru.turishev.ipireader.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileUtils {
    @Value("${upload.path}")
    private String uploadPath;
    public void saveFile(Long taskid, MultipartFile file) throws IOException {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            file.transferTo(new File(uploadPath+"/"+file.getOriginalFilename()));
    }
}
