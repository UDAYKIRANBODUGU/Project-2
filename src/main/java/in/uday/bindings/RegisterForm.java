package in.uday.bindings;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterForm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userid;
	private String username;
	private String userEmail;
	private String userpwd;
	private Integer countryId;
	private Integer stateId;
	private Integer cityId;
	private String Pwdupdated;
	@CreationTimestamp
	private LocalDateTime Createddate;
	@UpdateTimestamp
	private LocalDateTime Updatedate;

}



