package lab05.ex3;


import java.util.List;

public class Lab05ex3 {
    public static void main(String[] args) {
        Movie m=new Movie.Builder("Star Wars",1977)
                .director(new Person("George Lucas"))
                .writer(new Person("George Lucas"))
                .cast(List.of(new Person("Luke Skywalker"),new Person("Leia Organa"),new Person("Han Solo"),new Person("C-3PO")))
                .locations(List.of(new Place("Tatooine"),new Place("Coruscant")))
                .languages(List.of("English","German"))
                .genres(List.of("Action","Sci-Fi"))
                .isTelevision(true)
                .isNetflix(true)
                .isIndependent(true)
                .build();
        System.out.println(m);
    }
}
