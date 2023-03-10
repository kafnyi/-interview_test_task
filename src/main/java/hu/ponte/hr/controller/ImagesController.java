package hu.ponte.hr.controller;


import hu.ponte.hr.model.ImageMeta;
import hu.ponte.hr.services.ImageService;
import hu.ponte.hr.services.ImageStore;
import hu.ponte.hr.services.PictureRepository;
import hu.ponte.hr.services.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController()
@RequestMapping("api/images")
public class ImagesController {

	private final PictureRepository pictureRepository;
	private final SignService signService;
	private final ImageService imageService;
	private final ImageStore imageStore;

	@Autowired
	public ImagesController(PictureRepository pictureRepository, SignService signService, ImageService imageService, ImageStore imageStore) {
		this.pictureRepository = pictureRepository;
		this.signService = signService;
		this.imageService = imageService;
		this.imageStore = imageStore;
	}

	@GetMapping("meta")
	public List<ImageMeta> listImages() {
		return imageService.listAllImage();
	}

	@GetMapping("preview/{id}")
	public String getImage(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
		return imageStore.getOriginal(pictureRepository.findById(Long.parseLong(id)).get().getName());
	}

}
