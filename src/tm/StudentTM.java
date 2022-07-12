package tm;


import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentTM {
    String student_id;
    String student_name;
    String email;
    String contact;
    String nic;
    Button delete;
    Button update;
}
