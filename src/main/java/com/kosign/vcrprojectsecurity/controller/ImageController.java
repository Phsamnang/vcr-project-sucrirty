package com.kosign.vcrprojectsecurity.controller;


import com.kosign.vcrprojectsecurity.service.filesService.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ImageController {

    private final FileService imageService;

    @PostMapping("/image")
    public String upload(@RequestParam("file") MultipartFile multipartFile) {
        return imageService.upload(multipartFile);
    }
}