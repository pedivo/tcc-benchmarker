package br.com.machado.pedro.ivo.entity.generic;

import java.util.Date;

public abstract class SimpleEntity {

	protected Long		id;
	protected String	firstname;
	protected String	lastname;
	protected Date		birthday;
	protected String	city;
	protected String	email;

	public abstract void setId(Long id);

	public abstract Long getId();

	public abstract void setFirstname(String firstname);

	public abstract String getFirstname();

	public abstract void setLastname(String lastname);

	public abstract String getLastname();

	public abstract void setBirthday(Date birthday);

	public abstract Date getBirthday();

	public abstract void setCity(String city);

	public abstract String getCity();

	public abstract void setEmail(String email);

	public abstract String getEmail();

	@Override
	public String toString() {
		return "SimpleEntity [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", birthday=" + birthday + ", city=" + city + ", email=" + email
				+ "]";
	}

}
