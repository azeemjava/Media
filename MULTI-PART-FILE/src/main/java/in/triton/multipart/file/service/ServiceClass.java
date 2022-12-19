package in.triton.multipart.file.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import in.triton.multipart.file.entity.ImageData;
import in.triton.multipart.file.repository.Repository;
import in.triton.multipart.file.util.ImageUtil;

@Service
public class ServiceClass {
	
@Autowired
private Repository repository;

    public String uploadImage(MultipartFile file) throws IOException {
    	
    	System.out.println(ImageUtil.compressImage(file.getBytes()));

ImageData imageData = repository.save(ImageData.builder()        		
                                               .name(file.getOriginalFilename())
                                               .type(file.getContentType())
                                               .imageData(ImageUtil.compressImage(file.getBytes())).build());

if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName) throws IOException{
    	
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        byte[] images=ImageUtil.decompressImage(dbImageData.get().getImageData());
        
    	

	
        
        return images;
    }
    
    
} 

