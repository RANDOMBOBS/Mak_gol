package com.org.makgol.boards;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {

	public String upload(MultipartFile file) {

		boolean result = false;

		String fileName = file.getOriginalFilename();
		String fileExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		String uploadDirectory = "C:\\board\\upload";

		UUID uuid = UUID.randomUUID();
		String uniqueName = uuid.toString().replaceAll("-", "");

		File saveFile = new File(uploadDirectory + "\\" + uniqueName + fileExtension);

		if (!saveFile.exists())
			saveFile.mkdirs();

		try {
			file.transferTo(saveFile);
			result = true;

		} catch (Exception e) {
			e.printStackTrace();

		}
		System.out.println("결과는?"+result);
		if (result) {
			return uniqueName + fileExtension;
		} else {
			return null;
		}

	}

}
