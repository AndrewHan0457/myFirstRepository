/**
 * 
 */
package Customers;

import InterfacesAndEnums.Filter;
import InterfacesAndEnums.Mode;

/**
 * @author Andrew Han
 *
 */
public class Customer {
	private String name;
	private int age;
	private String tel;
	private Property property;
	private Mode mode;
	
	private String account;
	private String pwd;
	
	public Customer(String name) {
		this.name = name;
	}
	
	public void search(Filter [] f) {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}
	
	
}
