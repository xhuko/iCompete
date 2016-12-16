package com.icompete.sampledata;

import com.icompete.entity.*;
import com.icompete.enums.SportType;
import com.icompete.enums.UserType;
import com.icompete.service.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 * @version 14/12/2016
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    @Autowired
    private EventService eventService;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private ResultService resultService;
    @Autowired
    private SportService sportService;
    @Autowired
    private UserService userService;

    public void loadData() {
        Sport sportTennis = createSport("Tennis", "Tennis is a racket sport that can be played individually against a single opponent (singles).", SportType.SUMMER);
        Sport sportTableTennis = createSport("Table tennis", "Table tennis, also known as ping pong, is a sport in which two or four players hit a lightweight ball back and forth across a table using a small bat. ", SportType.INDOOR);
        Sport sportDarts = createSport("Darts", "Darts is a form of throwing game in which small missiles are thrown at a circular dartboard fixed to a wall.", SportType.INDOOR);
        Sport sportGolf = createSport("Golf", "Golf is a club and ball sport in which players use various clubs to hit balls into a series of holes on a course in as few strokes as possible.", SportType.OUTDOOR);
        Sport sportSnowboarding = createSport("Snowboarding", "Snowboarding is a recreational activity and Olympic and Paralympic sport that involves descending a snow-covered slope while standing on a snowboard attached to a rider's feet.", SportType.WINTER);

        User userAdmin = createUser("admin@icompete.com", "admin", "admin", UserType.ADMIN, "Address", "Admin", "iCompete", getDate(2016,12,14), new HashSet<Sport>());
        User userSekan = createUser("sekan@icompete.com", "peter", "sekan", UserType.SPORTSMAN, "Medze 47, Lipt. Mikulas, 031 05, Slovakia", "Peter", "Sekan", getDate(1994,9,3), new HashSet<Sport>(CollectionUtils.arrayToList(new Sport[]{sportTableTennis, sportTennis, sportDarts})));
        User userKondakciu = createUser("kondakciu@icompete.com", "xhulio", "kondakciu", UserType.SPORTSMAN, "Lushnje, Albania", "Xhulio", "Kondakciu", getDate(1993,1,1), new HashSet<Sport>(CollectionUtils.arrayToList(new Sport[]{sportGolf})));
        User userBohumel = createUser("bohumel@icompete.com", "branislav", "bohumel", UserType.SPORTSMAN, "Ziar nad Hronom, Slovakia", "Branislav", "Bohumel", getDate(1993,6,9), new HashSet<Sport>(CollectionUtils.arrayToList(new Sport[]{sportSnowboarding})));

        Event eventDarts = createEvent("Darts 2016", "FI MUNI",sportDarts, 4, getDate(2016,12,1), getDate(2016,12,4), new HashSet<String>(CollectionUtils.arrayToList(new String[]{
                "In a game of 501 the object is for one player to be the first to reach zero from starting total of 501.",
                "In simple terms, after three darts are thrown, the throwing player subtracts the total scored from his current total until he reaches zero.",
                "In order to reach zero each player must finish by throwing a double i.e. if player one has 36 remaining he must hit double 18 to win, while if player two has 45 remaining he must hit single 5, double 20 to win - or a another combination of scores provided the final dart scores on a double."
        })));
        
        Event eventSnowboarding = createEvent("Jasna Snowboarding 2017", "Jasna, Slovakia",sportSnowboarding, 10, getDate(2017,1,20), getDate(2016,1,30), new HashSet<String>(CollectionUtils.arrayToList(new String[]{
                "First man on the bottom of track will become winner.",
        })));

        Registration registrationSekanDarts = createRegistration(eventDarts, userSekan);
        Registration registrationKondakciuDarts = createRegistration(eventDarts, userKondakciu);
        Registration registrationBohumelDarts = createRegistration(eventDarts, userBohumel);
        Registration registrationSekanSnowboarding = createRegistration(eventSnowboarding, userSekan);
        Registration registrationKondakciuSnowboarding = createRegistration(eventSnowboarding, userKondakciu);

        Result resultDartsKondakciu = createResult(registrationKondakciuDarts, 1);
        Result resultDartsSekan = createResult(registrationSekanDarts, 2);
        Result resultDartsBohumel = createResult(registrationBohumelDarts, 3);
    }

    private Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    private User createUser(String email, String userName, String password, UserType userType, String address, String firstName, String lastName, Date birthDate, Set<Sport> preferredSports) {
        User u = new User();
        u.setEmail(email);
        u.setUserName(userName);
        u.setUserType(userType);
        u.setAddress(address);
        u.setBirthDate(birthDate);
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setPreferredSports(preferredSports);
        Long id = userService.createUser(u, password);
        return userService.getUserById(id);
    }

    private Sport createSport(String name, String description, SportType sportType) {
        Sport s = new Sport();
        s.setName(name);
        s.setType(sportType);
        s.setDescription(description);
        sportService.create(s);
        return s;
    }

    private Event createEvent(String name, String address, Sport sport, int capacity, Date startDate, Date endDate, Set<String> rules) {
        Event e = new Event();
        e.setName(name);
        e.setAddress(address);
        e.setSport(sport);
        e.setCapacity(capacity);
        e.setStartDate(startDate);
        e.setEndDate(endDate);
        
        Set<Rule> r = new HashSet<Rule>();
        for (String rule : rules) {
            Rule rr = new Rule();
            rr.setEvent(e);
            rr.setText(rule);
        }
        e.setRules(r);
        return eventService.create(e);
    }

    private Registration createRegistration(Event event, User user) {
        Registration r = new Registration();
        r.setEvent(event);
        r.setUser(user);
        registrationService.create(r);
        return r;
    }

    private Result createResult(Registration registration, int position) {
        Result r = new Result();
        r.setRegistration(registration);
        r.setPosition(position);
        resultService.create(r);
        return r;
    }
}
