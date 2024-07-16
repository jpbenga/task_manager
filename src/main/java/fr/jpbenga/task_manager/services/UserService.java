package fr.jpbenga.task_manager.services;

import fr.jpbenga.task_manager.models.User;
import fr.jpbenga.task_manager.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    
    public User findUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }
    
    public User updateUser(Long id, User userToUpdate){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            user.get().setUsername(userToUpdate.getUsername());
            user.get().setPassword(userToUpdate.getPassword());
            user.get().setRole(userToUpdate.getPassword());
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return (UserDetails) user.get();
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
    
    public void deleteUser(Long id){
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return true;
    }

    public String generateToken(UserDetails userDetails) {
        // Implémentez la génération de token JWT ici
        return "generated-jwt-token";
    }
}
