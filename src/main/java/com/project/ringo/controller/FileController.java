package com.project.ringo.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/upload")
@RestController
public class FileController {
	@Value("${filepath}")
	String filepath;
	// filepath는 application.properties에서 관리 중.

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<List<String>> upload(@RequestPart List<MultipartFile> files) throws Exception {

		List<String> fileNameList = new ArrayList<>();

		SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String formatDate = sdt.format(date);
		String datePath = formatDate.replace("-", File.separator);

		String imgPath = filepath + "/img/";
		String fileNamePrefix = "ringoImage";
		for (MultipartFile file : files) {
			String originalfileName = file.getOriginalFilename();

			Files.createDirectories(Paths.get(imgPath)); // 존재하지 않으면 디렉토리를 생성
			
			System.out.println(imgPath);
			String fileName = fileNamePrefix + originalfileName;
			
			System.out.println(fileName);
			File dest = new File(imgPath + fileName);
			System.out.println(fileName);
			file.transferTo(dest);

			fileNameList.add(fileName);
		}
		return new ResponseEntity<>(fileNameList, HttpStatus.CREATED);
	}

}
