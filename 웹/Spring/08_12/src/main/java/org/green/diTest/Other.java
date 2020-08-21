package org.green.diTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Other {
	@Autowired
	@Qualifier("mySome")
	private Some some;
	
	public Other() {
	}

	public Other(Some some) {
		super();
		this.some = some;
	}

	public Some getSome() {
		return some;
	}

	public void setSome(Some some) {
		this.some = some;
	}

	@Override
	public String toString() {
		return "Other [some=" + some + "]";
	}
	
}
