package org.revo.jsonser;

public class View {
    public interface Default {

    }

    public interface BasicUser {

    }

    public interface DetailedUser {

    }

    public interface BasicPhone {

    }

    public interface DetailedPhone {

    }


    public interface BasicEmail {

    }

    public interface DetailedEmail {

    }

    public interface FullUserView extends Default, BasicUser, DetailedUser, BasicPhone, BasicEmail {

    }

}
