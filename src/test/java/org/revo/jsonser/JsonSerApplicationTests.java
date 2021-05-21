package org.revo.jsonser;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.junit.jupiter.api.Test;
import org.revo.jsonser.domain.Email;
import org.revo.jsonser.domain.Phone;
import org.revo.jsonser.domain.Status;
import org.revo.jsonser.domain.User;
import org.revo.jsonser.repository.EmailRepository;
import org.revo.jsonser.repository.PhoneRepository;
import org.revo.jsonser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@DataJpaTest
class JsonSerApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private EmailRepository emailRepository;
    private final static ObjectMapper mapper = cachingObjectMapper();

    @Test
    void contextLoads() throws JsonProcessingException {
        initDb();
        JsonPage<User> page = new JsonPage<>(StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList()));
        String json = mapper.writerWithView(View.FullUserView.class).writeValueAsString(page);
        System.out.println(json);
        System.out.println("json.contains(PersistentBag): "+json.contains("org.hibernate.collection.internal.PersistentBag"));
        System.out.println("json.contains(PersistentSet): "+json.contains("org.hibernate.collection.internal.PersistentSet"));
    }


    void initDb() {
        User savedUser = userRepository.save(new User(null, "ashraf", Status.ACTIVE, new ArrayList<>(), new HashSet<>(), null));
        Phone savedPhone1 = phoneRepository.save(new Phone(null, "p1", savedUser));
        Phone savedPhone2 = phoneRepository.save(new Phone(null, "p2", savedUser));

        Email savedEmail1 = emailRepository.save(new Email(null, "e1", savedUser));
        Email savedEmail2 = emailRepository.save(new Email(null, "e2", savedUser));

        savedUser.setPhones(new ArrayList<>(Arrays.asList(savedPhone1, savedPhone2)));
        savedUser.setEmails(new HashSet<>(new ArrayList<>(Arrays.asList(savedEmail1, savedEmail2))));
        savedUser = userRepository.save(savedUser);

    }

    public static ObjectMapper cachingObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_OBJECT);

        mapper.registerModule(new Hibernate5Module()
                        .configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true)
//                        .configure(Hibernate5Module.Feature.REQUIRE_EXPLICIT_LAZY_LOADING_MARKER, false)
//                .configure(Hibernate5Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS, false)
                        .configure(Hibernate5Module.Feature.REPLACE_PERSISTENT_COLLECTIONS, true)
        );
        return mapper;
    }

}
