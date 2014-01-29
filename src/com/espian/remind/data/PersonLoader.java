package com.espian.remind.data;

import java.util.concurrent.Future;

public interface PersonLoader {

    Future loadPhoto(Person person, PhotoRequestCallback callback);

}
