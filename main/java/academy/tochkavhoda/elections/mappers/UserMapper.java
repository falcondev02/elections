package academy.tochkavhoda.elections.mappers;

import academy.tochkavhoda.elections.dto.request.RegisterVoterDtoRequest;
import academy.tochkavhoda.elections.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User REGISTER_VOTER_DTO_REQUEST(RegisterVoterDtoRequest registerVoterDtoRequest);

}

