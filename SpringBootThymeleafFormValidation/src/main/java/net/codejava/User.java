package net.codejava;


import java.util.Date;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

public class User {
	@NotNull(message = "Tên bị null!")
	@Pattern(regexp = "[a-zA-Z][a-zA-Z ]+", message = "Tên chứa kí tự cấm!")
	@Length(min = 3, max = 200, message = "Tên phải từ 3 đến 200 kí tự!")
	private String name;

	@NotBlank(message = "Không được để trông ô địa chỉ")
	private String address;

	@NotEmpty(message = "Số điện thoại không được trống")
	@Pattern(regexp = "^0\\d{9}$", message = "Nhập đúng định dạng của số điện thoại")
	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
