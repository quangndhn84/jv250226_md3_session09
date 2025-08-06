package ra.com.service.imp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ra.com.service.UploadFileService;

import java.util.Map;

@Service
public class UploadFileServiceImp implements UploadFileService {
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public String uploadFile(MultipartFile file) {
        //1. Lấy ra tên ảnh trong file
        //apple.jpg
        String originalFilename = file.getOriginalFilename();
        //apple
        if (originalFilename != null && originalFilename.contains(".")) {
            originalFilename = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        }
        //2. upload file lên cloudiary giữ nguyên tên ảnh
        Map cloudiaryParams = ObjectUtils.asMap("public_id", originalFilename);
        Map cloudResult = null;
        try {
            cloudResult = cloudinary.uploader().upload(file.getBytes(), cloudiaryParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloudResult.get("url").toString();
    }
}
