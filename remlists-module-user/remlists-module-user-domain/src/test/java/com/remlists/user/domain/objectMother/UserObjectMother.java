package com.remlists.user.domain.objectMother;

import com.remlists.user.domain.entities.User;
import com.remlists.user.domain.entities.UserAddress;
import com.remlists.user.domain.valueObjects.*;

public final class UserObjectMother {

    public static ShortName createShortName(){
        return new ShortName("dsolerac");
    }
    public static ShortName createNotValidShortName(){
        return new ShortName("dsolerac#@");
    }
    public static EmailAddress createEmailAddress(){
        return new EmailAddress("dsc@g.com");
    }
    public static EmailAddress createNotValidEmailAddress(){
        return new EmailAddress("dsol.erac.gmail.com");
    }
    public static Password createPassword(){
        return new Password("123456");
    }
    public static Password createNotValidPassword(){
        return new Password("pas");
    }

    public static Country createSpainCountry(){
        return new Country("ES", "Spain");
    }
    public static EmailVerified createVerifiedEmail(){
        return new EmailVerified(true);
    }
    public static Language createSpanishLanguage(){
        return new Language("es", "Spanish");
    }
    public static Twitter createValidTwitterAccount(){
        return new Twitter("@dsolerac");
    }
    public static DateOfBirth createDateOfBirth(){
        return new DateOfBirth(1955, 05, 25 );
    }
    public static MobilePhone createMobilePhone(){
        return new MobilePhone("852993364");
    }
    public static UserStatus createActiveUserStatus(){
        return UserStatus.ACTIVE;
    }
    public static URLWeb createValidUrlWeb(){
        return new URLWeb("http://www.dsolerac.com");
    }
    public static UserDescription createDescription(){
        return new UserDescription("asdfadfadfasdf");
    }



    public static User.UserBuilder createBasicUser(){

        ShortName shortName = createShortName();
        EmailAddress email = createEmailAddress();
        Password password = createPassword();

        return new User.UserBuilder(shortName, email,password);
    }
    public static User.UserBuilder createUser_WithSpainCountry(){

        return createBasicUser().withCountry(createSpainCountry());
    }
    public static User.UserBuilder createUser_WithSpanishLanguage(){
        return createBasicUser().withLanguage(createSpanishLanguage());
    }
    public static User.UserBuilder createUser_WithTwitter(){
        return createBasicUser().withTwitter(createValidTwitterAccount());
    }
    public static User.UserBuilder createUser_WithDateOfBirth(){
        return createBasicUser().withDateOfBirth(createDateOfBirth());
    }
    public static User.UserBuilder createUser_WithMobilePhone(){
        return createBasicUser().withMobilePhone(createMobilePhone());
    }
    public static User.UserBuilder createUser_WithActiveUserStatus(){
        return createBasicUser().withUserStatus(createActiveUserStatus());
    }
    public static User.UserBuilder createUser_WithUrlWeb(){
        return createBasicUser().withURLWeb(createValidUrlWeb());
    }
    public static User.UserBuilder createUser_WithDescription(){
        return createBasicUser().withUserDescription(createDescription());
    }



}
