package com.example.crmbtf.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "b_user")
public class TelegramUser {

    @Id
    @Column(name = "chatId", nullable = false)
    private Long chatId;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "localState")
    private String localeState;
    @Enumerated(EnumType.STRING)
    @Column(name = "globalState")
    public botstate globalState;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "role", columnDefinition = "varchar(20) default 'USER'")
    private String role;
    @Column(name = "authorizationToken")
    private String authorizationToken;


    public TelegramUser(Long chatId, String firstName, String lastName) {
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public enum botstate {
        START,
        MAINIDEA,
        ALGORITM_AND_STRUCTURE,
        COLLECTION,
        EXCEPTION,
        FUNCTIONAL_INTERFACE,
        GENERIC,
        JAVA8,
        JPA_HIBERNATE,
        MULTITHREADING,
        OOP_IN_JAVA,
        PATTERN,
        PROCEDURE_JAVA,
        SERIALIZE_AND_COPY,
        SPRING,
        SQLAndDB,
        STREAM_API,
        PROCEDURE,
        TYPE,
        CHAR,
        BOOLEAN,
        OBERTKA,
        PACKUNPUCK,
        PRIVEDENIE,
        INT,
        STRINGPULL,
        STRINGNUANCE,
        STRINGNOEDIN,
        STRINGNOPASS,
        STRINGFINAL,
        STRINGKEY,
        

        KROSSPLATFORM,
        BENEFITS,
        LIMITATIONS,
        JDK,
        JVM,
        BYTECODE,
        CLASSLOADER,
        JIT,
        GC,
        HEAPANDSTACK,
        TEORKA,
        JAVA,
        OOP,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        GETRESULT,
        SPECIALISTS,
        TEST,
        ABOUT,
        COSMETICS,
        SERVICES,
        ADDRESS,
        DOCTORS,
        SHOW_SITE,
        MAIN_MENU,
        MY_APPOINTMENTS,
        START_BOT_CHATTING,
        APPOINTMENT_TO_DOCTOR,
        CONTACT,
        CALL_BACK,
        BEAUTICIANS,
        LOGIN,
        SEND_APPOINTMENT
    }
}

