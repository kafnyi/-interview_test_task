package hu.ponte.hr.services;

import hu.ponte.hr.model.ImageMeta;
import hu.ponte.hr.model.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

	private final PictureRepository pictureRepository;
	private final SignService signService;

	@Autowired
	public ImageService(PictureRepository pictureRepository, SignService signService) {
		this.pictureRepository = pictureRepository;
		this.signService = signService;
	}

	public List<ImageMeta> listAllImage() {
		List<ImageMeta> imageMetaList = new ArrayList<>();
		for (Picture picture : pictureRepository.findAll()) {
			imageMetaList.add(this.createImageMetaFromPicture(picture));
		}
		return imageMetaList;
	}

	public ImageMeta createImageMetaFromPicture(Picture picture) {
		return new ImageMeta(String.valueOf(picture.getId()), picture.getName(), picture.getMimeType(), picture.getSize(), picture.getDigitalSign());
	}
}
