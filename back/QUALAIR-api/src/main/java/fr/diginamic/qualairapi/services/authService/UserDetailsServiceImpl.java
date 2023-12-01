package fr.diginamic.qualairapi.services.authService;

import fr.diginamic.qualairapi.entities.User;
import fr.diginamic.qualairapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

import static java.util.Collections.singletonList;

/**
 * Service pour la gestion des détails de l'utilisateur.
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Charge les détails de l'utilisateur par son nom d'utilisateur (email).
     *
     * @param email Le nom d'utilisateur (email) de l'utilisateur à charger.
     * @return Les détails de l'utilisateur.
     * @throws UsernameNotFoundException Si l'utilisateur n'est pas trouvé.
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {
        System.out.println("loadUser");
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        User user = userOptional
                .orElseThrow(() -> new UsernameNotFoundException("No user " +
                        "Found with username: " + email));

        return new org.springframework.security
                .core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                getAuthorities(user.getRole().name())
        );
    }

    /**
     * Récupère les autorisations de l'utilisateur.
     *
     * @param role Le rôle de l'utilisateur.
     * @return Une collection d'autorisations.
     */
    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }
}
