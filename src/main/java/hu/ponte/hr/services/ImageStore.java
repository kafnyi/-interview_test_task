package hu.ponte.hr.services;

import hu.ponte.hr.model.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageStore {

	private final PictureRepository pictureRepository;

	private final SignService signService;

	@Autowired
	public ImageStore(PictureRepository pictureRepository, SignService signService) {
		this.pictureRepository = pictureRepository;
		this.signService = signService;
	}

	public Picture store(MultipartFile input) {
		Picture picture = new Picture(input);
		try {
			picture.setDigitalSign(signService.sign(new String(input.getBytes())));
			return pictureRepository.save(picture);
		} catch (IOException e) {
			System.out.println(e);
			return null;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
