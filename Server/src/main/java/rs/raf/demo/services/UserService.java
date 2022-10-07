package rs.raf.demo.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.codec.digest.DigestUtils;
import rs.raf.demo.entities.User;
import rs.raf.demo.repositories.user.UserRepository;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class UserService {

    @Inject
    UserRepository userRepository;

    public User addUser(User user) {
        return this.userRepository.addUser(user);
    }

    public List<User> allUsers() {
        return this.userRepository.allUsers();
    }

    public List<User> pageUsers(Integer page, Integer limit) {
        return this.userRepository.pageUsers(page, limit);
    }

    public User findUser(Integer id) {
        return this.userRepository.findUser(id);
    }

    public User editUser(User user) {
        return this.userRepository.editUser(user);
    }

    public User setUserStatus(Integer id) {
        this.userRepository.setUserStatus(id);
        return this.userRepository.findUser(id);
    }

    public void deleteUser(Integer id) {
        this.userRepository.deleteUser(id);
    }

    public String login(String email, String password){
        String hashedPassword = DigestUtils.sha256Hex(password);

        User user = this.userRepository.findUserByEmail(email);
        if (user == null || !user.getPassword().equals(hashedPassword)) {
            return null;
        }

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24*60*60*1000); // One day

        Algorithm algorithm = Algorithm.HMAC256("secret");

        // JWT-om mozete bezbedono poslati informacije na FE
        // Tako sto sve sto zelite da posaljete zapakujete u claims mapu
        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(email)
                .withClaim("id", user.getId())
                .withClaim("name", user.getName())
                .withClaim("surname", user.getSurname())
                .withClaim("usertype", user.getUserType())
                .withClaim("status", user.getStatus())
                .sign(algorithm);
    }

    public boolean isAuthorized(String token){
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);

        String email = jwt.getSubject();
//        jwt.getClaim("role").asString();

        User user = this.userRepository.findUserByEmail(email);

        if (user == null){
            return false;
        }

        return true;
    }
}
