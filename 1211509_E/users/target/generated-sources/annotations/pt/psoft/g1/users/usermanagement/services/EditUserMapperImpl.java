package pt.psoft.g1.users.usermanagement.services;

import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pt.psoft.g1.users.usermanagement.model.Role;
import pt.psoft.g1.users.usermanagement.model.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-04T18:15:39+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class EditUserMapperImpl extends EditUserMapper {

    @Override
    public User create(CreateUserRequest request) {
        if ( request == null ) {
            return null;
        }

        User user = new User();

        if ( user.getAuthorities() != null ) {
            Set<Role> set = stringToRole( request.getAuthorities() );
            if ( set != null ) {
                user.getAuthorities().addAll( set );
            }
        }
        user.setPassword( map( request.getPassword() ) );
        user.setName( map( request.getName() ) );
        user.setUsername( map( request.getUsername() ) );

        return user;
    }

    @Override
    public void update(EditUserRequest request, User user) {
        if ( request == null ) {
            return;
        }

        if ( user.getAuthorities() != null ) {
            user.getAuthorities().clear();
            Set<Role> set = stringToRole( request.getAuthorities() );
            if ( set != null ) {
                user.getAuthorities().addAll( set );
            }
        }
        if ( request.getPassword() != null ) {
            user.setPassword( map( request.getPassword() ) );
        }
        if ( request.getName() != null ) {
            user.setName( map( request.getName() ) );
        }
        if ( request.getUsername() != null ) {
            user.setUsername( map( request.getUsername() ) );
        }
    }
}
