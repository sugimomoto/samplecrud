package sugimomoto.samplecrud.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class SleepParameter {

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
