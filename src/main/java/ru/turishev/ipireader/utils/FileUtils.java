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
    private static final String path="http://ipi-manager3/";

    public void saveFile(Long taskid, MultipartFile file) throws IOException {
            File uploadDir = new File(uploadPath+"/"+getPathByNumber(taskid));
            if(!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            file.transferTo(new File(uploadPath+"/"+getPathByNumber(taskid)+"/"+file.getOriginalFilename()));
    }

    public static String getPathByNumber(Long id) {
        Integer pathPart = (id%10000!=0)?(int)Math.floor(id/10000):(int)Math.floor(id/10000-1);
        if(pathPart<1) {
            return "1-10000/"+String.valueOf(id)+"/";
        }
        else {
            return pathPart.toString() + "0001-" + (++pathPart).toString() + "0000/" + String.valueOf(id) + "/";
        }
    }
    public static String getDownloadPathByNumber(Long id) {
            return path+getPathByNumber(id);
    }


}
