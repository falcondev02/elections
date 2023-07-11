package academy.tochkavhoda.elections.dto.response;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AllCandidatesDtoResponse {
    private Set<ProgramDtoResponse> allCandidates = new HashSet<>();

    public void addNewResponse( ProgramDtoResponse programDtoResponse) {
        allCandidates.add(programDtoResponse);
    }
}
