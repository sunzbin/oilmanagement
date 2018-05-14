/**
 * @Description:
 * @Author:梁英男
 * @Date:2018年5月9日
 */
package com.jeefw.model.equipment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.jeefw.model.equipment.param.AscIIParameter;

/**
 * @Author:梁英男
 * @Date:2018年5月9日
 */
@Entity
@Table(name = "ascii")
@Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties", "success", "message", "sortColumnsString", "flag" })
public class AscII extends AscIIParameter{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	/**
	 * 二进制
	 */
	@Column(name = "bin",length = 20, nullable = false, unique = true)
	private String bin;
	
	/**
	 * 八进制
	 */
	@Column(name = "qoc",length = 20, nullable = false, unique = true)
	private String qoc;
	
	/**
	 * 十进制
	 */
	@Column(name = "dec",length = 20, nullable = false, unique = true)
	private String dec;
	
	/**
	 * 十六进制
	 */
	@Column(name = "hex",length = 20, nullable = false, unique = true)
	private String hex;
	
	/**
	 * 缩写/字符
	 */
	@Column(name = "characters",length = 20, nullable = false, unique = true)
	private String characters;
	
	/**
	 * 解释
	 */
	@Column(name = "explain",length = 20, nullable = false, unique = true)
	private String explain;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBin() {
		return bin;
	}

	public void setBin(String bin) {
		this.bin = bin;
	}

	public String getQoc() {
		return qoc;
	}

	public void setQoc(String qoc) {
		this.qoc = qoc;
	}

	public String getDec() {
		return dec;
	}

	public void setDec(String dec) {
		this.dec = dec;
	}

	public String getHex() {
		return hex;
	}

	public void setHex(String hex) {
		this.hex = hex;
	}

	public String getCharacters() {
		return characters;
	}

	public void setCharacters(String characters) {
		this.characters = characters;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bin == null) ? 0 : bin.hashCode());
		result = prime * result + ((characters == null) ? 0 : characters.hashCode());
		result = prime * result + ((dec == null) ? 0 : dec.hashCode());
		result = prime * result + ((explain == null) ? 0 : explain.hashCode());
		result = prime * result + ((hex == null) ? 0 : hex.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((qoc == null) ? 0 : qoc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AscII other = (AscII) obj;
		if (bin == null) {
			if (other.bin != null)
				return false;
		} else if (!bin.equals(other.bin))
			return false;
		if (characters == null) {
			if (other.characters != null)
				return false;
		} else if (!characters.equals(other.characters))
			return false;
		if (dec == null) {
			if (other.dec != null)
				return false;
		} else if (!dec.equals(other.dec))
			return false;
		if (explain == null) {
			if (other.explain != null)
				return false;
		} else if (!explain.equals(other.explain))
			return false;
		if (hex == null) {
			if (other.hex != null)
				return false;
		} else if (!hex.equals(other.hex))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (qoc == null) {
			if (other.qoc != null)
				return false;
		} else if (!qoc.equals(other.qoc))
			return false;
		return true;
	}

	/**
	 * @param id
	 * @param bin
	 * @param qoc
	 * @param dec
	 * @param hex
	 * @param characters
	 * @param explain
	 */
	public AscII(Long id, String bin, String qoc, String dec, String hex, String characters, String explain) {
		super();
		this.id = id;
		this.bin = bin;
		this.qoc = qoc;
		this.dec = dec;
		this.hex = hex;
		this.characters = characters;
		this.explain = explain;
	}

	/**
	 * 
	 */
	public AscII() {
		super();
	}
}
