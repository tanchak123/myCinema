package mycinema.security;

import mycinema.exceptions.AuthenticationException;
import mycinema.exceptions.RegistrationException;
import mycinema.lib.Inject;
import mycinema.lib.Service;
import mycinema.model.User;
import mycinema.service.ShoppingCartService;
import mycinema.service.UserService;
import mycinema.utill.HashUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class SecurityServiceImpl implements SecurityService {

    private static final Logger LOGGER = LogManager.getLogger(SecurityServiceImpl.class);

    @Inject
    private UserService userService;

    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User dbUser = userService.findByEmail(email);
        if (dbUser == null
                || !HashUtil.isValid(password, dbUser.getPassword(), dbUser.getSalt())) {
            throw new AuthenticationException();
        }
        LOGGER.info("Пользователь успешно залогинился!");
        return dbUser;
    }

    @Override
    public User register(String email, String password) {
        User dbUser = userService.findByEmail(email);
        if (dbUser != null) {
            try {
                throw new RegistrationException();
            } catch (RegistrationException e) {
                e.printStackTrace();
            }
        }
        dbUser = new User();
        dbUser.setEmail(email);
        dbUser.setSalt(HashUtil.getSalt());
        dbUser.setPassword(HashUtil.hashPassword(password, dbUser.getSalt()));
        dbUser = userService.add(dbUser);
        shoppingCartService.registerNewShoppingCart(dbUser);
        LOGGER.info("Пользователь успешно зарегистрирован!");
        return dbUser;
    }
}
