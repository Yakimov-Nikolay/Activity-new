package activity_new.activity_new.service.impl;

import activity_new.activity_new.service.CloudinaryService;
import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private static final String TEMP_FILE = "temp-file";
    private static final String URL = "url";
    private static final String PUBLIC_ID = "public_id";

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public CloudinaryImage upload (MultipartFile file) throws IOException {

        File tempFile = File.createTempFile(TEMP_FILE, file.getOriginalFilename());
        file.transferTo(tempFile);

        try {
            @SuppressWarnings("unchecked")
            Map<String, String> uploadResult = cloudinary.uploader()
                    .upload(tempFile, Map.of());

            String url = uploadResult.getOrDefault(URL, "https://previews.123rf.com/images/olejio/olejio1802/olejio180200041/95585020-funny-design-404-page-not-found-vector-illustration-geek-with-metal-detector-searching-the-big-data.jpg");
            String publicId = uploadResult.getOrDefault(PUBLIC_ID, "");

            return new CloudinaryImage()
                    .setPublicId(publicId)
                    .setUrl(url);
        }finally {
            tempFile.delete();
        }
    }
    @Override
    public boolean delete(String publicId) {
        try {
            this.cloudinary.uploader().destroy(publicId, Map.of());
        } catch (IOException e) {
          return false;
        }
        return true;
    }
}
