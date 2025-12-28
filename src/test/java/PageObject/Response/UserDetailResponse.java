package PageObject.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor //Generates a no-argument constructor (a constructor with no parameters).
@AllArgsConstructor //Generates a constructor with all fields as parameters.
public class UserDetailResponse {
    @JsonProperty("userId")
    private int userId;
    @JsonProperty("id")
    private int id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("body")
    private String body;

}
