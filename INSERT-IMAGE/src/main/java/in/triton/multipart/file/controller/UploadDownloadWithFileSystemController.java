package in.triton.multipart.file.controller;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.core.io.Resource;


import in.triton.multipart.file.response.FileUploadResponse;
import in.triton.multipart.file.service.FileStorageService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UploadDownloadWithFileSystemController {

    private FileStorageService fileStorageService;

    public UploadDownloadWithFileSystemController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("single/upload")
    FileUploadResponse singleFileUplaod(@RequestParam("file") MultipartFile file) {

        String fileName = fileStorageService.storeFile(file);

        ///http://localhost:8081/download/abc.jpg
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(fileName)
                .toUriString();

        String contentType = file.getContentType();

        FileUploadResponse response = new FileUploadResponse(fileName, contentType, url);

        return response;

    }

    ///////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/download/{fileName}")
    ResponseEntity<Resource> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = (Resource) fileStorageService.downloadFile(fileName);


        String mimeType;

        try {
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        mimeType = mimeType == null ? MediaType.APPLICATION_OCTET_STREAM_VALUE : mimeType;

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename())
                .body(resource);
    }

}
