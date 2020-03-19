package ru.turishev.ipireader.utils;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@Service
public class FileUtils {
    private static final String path="http://ipi-manager3/";
    private String uploadPath="c:/upload/";

    public void saveFile(Long taskid, MultipartFile file) throws IOException {
            uploadPath = uploadPath+getPathByNumber(taskid);
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            file.transferTo(new File(uploadPath+"/"+file.getOriginalFilename()));
    }

    public static String getDownloadPathByNumber(Long id) {
            return path + getPathByNumber(id);
    }

    private static String getPathByNumber(Long id) {
        Integer pathPart = (id%10000!=0)?(int)Math.floor(id/10000):(int)Math.floor(id/10000-1);
        if(pathPart<1) {
            return "1-10000/"+String.valueOf(id)+"/";
        }
        else {
            return pathPart.toString() + "0001-" + (++pathPart).toString() + "0000/" + String.valueOf(id) + "/";
        }
    }


}
