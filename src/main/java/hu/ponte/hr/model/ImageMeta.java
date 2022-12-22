package hu.ponte.hr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zoltan
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ImageMeta {
	private String id;
	private String name;
	private String mimeType;
	private long size;
	private String digitalSign;

}
