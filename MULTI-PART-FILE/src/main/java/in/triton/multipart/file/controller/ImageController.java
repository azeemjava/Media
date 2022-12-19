package in.triton.multipart.file.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import in.triton.multipart.file.service.ServiceClass;
import jakarta.servlet.ServletContext;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/image")
public class ImageController {
	
	
		@Autowired
		private ServiceClass service;

		@PostMapping
		public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
	
//			ClassLoader classLoader = getClass().getClassLoader();
//			String filename= classLoader.getResource("static/").getPath();
//			
//			
//			File f = new File(filename);
//			Path path=Paths.get(f.getPath());
//			Files.write(path, file.getBytes());
			
//			Path source= Paths.get(this.getClass().getResource("/").getPath());
//			Path ok=Paths.get(source.toAbsolutePath()+"/static/");
//			
//			
//			boolean exists=Files.exists(ok);
//		
//			if(!exists) {
//				Files.createDirectories(ok);
//			}
//			Path ex = Paths.get(ok.toAbsolutePath()+ file.getOriginalFilename());
//			Files.write(ex, file.getBytes());
//			
			
			
			
			//String uploadImage = service.uploadImage(file);
			return ResponseEntity.ok(null);
			
			
		}

		@GetMapping("/{fileName}")
		public ResponseEntity<?> downloadImage(@PathVariable String fileName) throws IOException{
		
			
			
			byte[] imageData=service.downloadImage(fileName);
		
			return ResponseEntity.status(HttpStatus.OK)
					.contentType(MediaType.valueOf("image/png"))
					.body(imageData);
			
			
			
			

		}
		
		


}
