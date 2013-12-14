package com.anores.game.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "meta_profiles")
public class MetaProfile extends BaseEntityAudit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4361100797625418233L;

	@Column(name = "meta_key")
	private String metaKey;
	
	@Column(name = "meta_value")
	private String metaValue;
	
	@Column(name = "description")
	private String description;
	
	public MetaProfile(String key, String value) {
		this.metaKey = key;
		this.metaValue = value;
	}

	public String getMetaKey() {
		return metaKey;
	}

	public void setMetaKey(String metaKey) {
		this.metaKey = metaKey;
	}

	public String getMetaValue() {
		return metaValue;
	}

	public void setMetaValue(String metaValue) {
		this.metaValue = metaValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
