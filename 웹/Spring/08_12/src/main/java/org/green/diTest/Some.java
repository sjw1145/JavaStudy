package org.green.diTest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 * Annotation
 * 		@Component : bean으로 등록
 * 
 * 		@Controller
 * 		@Repository : DAO
 * 		@Service : Command
 */

@Component("someObj")
public class Some {
	
	@Value("test")
	private String contents;

	public Some(String contents) {
		super();
		this.contents = contents;
	}

	public Some() {
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "Some [contents=" + contents + "]";
	}
	
	
}
