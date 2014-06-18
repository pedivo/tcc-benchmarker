package br.com.machado.pedro.ivo.entity.mock;

import java.util.Date;
import br.com.machado.pedro.ivo.entity.generic.SimpleEntity;


public class SimpleEntityMock extends SimpleEntity {

	@Override
	public void setId(Long id) {
		super.id = id;		
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setFirstname(String firstname) {
		super.firstname = firstname;
	}

	@Override
	public String getFirstname() {
		return super.firstname;
	}

	@Override
	public void setLastname(String lastname) {
		super.lastname = lastname;
	}

	@Override
	public String getLastname() {
		return super.lastname;
	}

	@Override
	public void setBirthday(Date birthday) {
		super.birthday = birthday;
	}

	@Override
	public Date getBirthday() {
		return super.birthday;
	}

	@Override
	public void setCity(String city) {
		super.city = city;
	}

	@Override
	public String getCity() {
		return super.city;
	}

	@Override
	public void setEmail(String email) {
		super.email = email;
	}

	@Override
	public String getEmail() {
		return super.email;
	}
	
	

}
