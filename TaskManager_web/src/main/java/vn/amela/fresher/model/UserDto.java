package vn.amela.fresher.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]+", message = "Tên không chứa ký tự (!#$@_+,?.-)")
    @Length(min = 3, max = 100, message = "Username phải từ 3 trở lên.")
    private String username;

    @NotBlank(message = "Nhập password đầy đủ.")
    @Length(min = 6, message = "Password trên 6 ký tự.")
    private String password;

    @NotBlank(message = "Không được trống")
    private  String rpassword;

    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]+", message = "Tên không chứa ký tự (!#$@_+,?.-)")
    @Length(min = 3, message = "Name phải từ 3 ký tự trở lên.")
    private String name;
}
