package hu.ponte.hr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.util.Date;

@Entity
@Table(name = "pics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Picture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private long id;
	private String name;
	private String mimeType;
	private long size;
	@Lob
	private String digitalSign;
	@Lob
	private String originalBytes;
	@CreationTimestamp
	private Date created_at;
	@UpdateTimestamp
	private Date updated_at;

	public Picture(MultipartFile file) throws IOException {
		setName(file.getOriginalFilename());
		setMimeType(file.getContentType());
		setSize(file.getSize());
		setOriginalBytes(file.getBytes());
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

	public void setOriginalBytes(byte[] bytes){
		this.originalBytes = new String(bytes);
	}

}
