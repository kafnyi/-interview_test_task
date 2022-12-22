package hu.ponte.hr.services;

import hu.ponte.hr.model.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class ImageStore {

	private final String FILE_DIR_PATH = "W:/Projects/InteliJ/interview_test_task/originalimages/";
	private final PictureRepository pictureRepository;

	private final SignService signService;

	@Autowired
	public ImageStore(PictureRepository pictureRepository, SignService signService) {
		this.pictureRepository = pictureRepository;
		this.signService = signService;
	}

	public Picture store(MultipartFile input) throws IOException {
		Picture picture = new Picture(input);
		try {
			picture.setDigitalSign(signService.sign(new String(input.getBytes())));
			multipartFileToFile(input);
			return pictureRepository.save(picture);
		} catch (IOException e) {
			System.out.println(e);
			return null;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void multipartFileToFile(MultipartFile originalFile) throws IOException {
		MultipartFile file;
		file = originalFile;
		if (!new File(FILE_DIR_PATH).exists()) {
			new File(FILE_DIR_PATH).mkdir();
		}

		String orgName = file.getOriginalFilename();
		String filePath = FILE_DIR_PATH + orgName;
		File dest = new File(filePath);
		file.transferTo(dest);

	}

	public String getOriginal(String name) throws IOException {
		String filePath = FILE_DIR_PATH + name;
		FileInputStream fileInputStream = new FileInputStream(filePath);
		return new String(fileInputStream.readAllBytes());
	}
}
