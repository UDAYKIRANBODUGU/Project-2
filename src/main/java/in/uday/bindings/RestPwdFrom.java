package in.uday.bindings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestPwdFrom {
	
    private Integer userId;
    private String email;
	private String newPwd;
	private String confirmPwd;

}
