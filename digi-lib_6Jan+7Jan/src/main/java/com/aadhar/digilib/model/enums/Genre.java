package com.aadhar.digilib.model.enums;

// see it created a new package for enums
// here we are going to tell the database that what type of Book genre we can have
// and also we can assign them any values as per requirement from the client
// we will come back here post completing Book class
public enum Genre {
    // This is not a java class . Observe it uses the enum keyword. Now enum is just like giving the users some hardcoded options to chose from
    // By using Genre as enum , we are telling that in our digi library project we only have books of these all genres only
    //which we are explicity mentioning here in this ENUM .

    // think which all genres books have you seen .. ?

    FICTION,

    NON_FICTION,

    PROGRAMMING

    //so right now we will limit the genre of books in our digital library project as these three genres..
    // we can anyday come back and introduce new Genres in this enum.. right.
    // now lets go back to author class and complete it.
}
