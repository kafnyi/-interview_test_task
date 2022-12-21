package hu.ponte.hr.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "pics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Picture {
	@Id
	private long id;
	private String name;
	private String mimeType;
	private long size;
	private String digitalSign;
	@CreationTimestamp
	private Date created_at;
	@UpdateTimestamp
	private Date updated_at;

	public Picture(long id, String name, String mimeType, long size, String digitalSign){
		this.id = id;
		setName(name);
		setMimeType(mimeType);
		setSize(size);
		setDigitalSign(digitalSign);
	}

	public Picture(String name, String mimeType, long size){
		setName(name);
		setMimeType(mimeType);
		setSize(size);
	}

	public Picture (MultipartFile file){
		setName(file.getOriginalFilename());
		setMimeType(file.getContentType());
		setSize(file.getSize());
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public void setDigitalSign(String digitalSign) {
		this.digitalSign = digitalSign;
	}

}
