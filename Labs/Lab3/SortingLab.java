import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.ActorMovieIMDB;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortingLab {
    public static void main(String[] args) throws Exception {
        //create the Bridges object, set credentials
        Bridges bridges = new Bridges(3, "cutching",
                "553004712356");
        DataSource ds = bridges.getDataSource();
        List<ActorMovieIMDB>movieData = null;
        List<ActorMovieIMDB> filteredMovieList = new ArrayList<>();
        try{
            movieData = ds.getActorMovieIMDBData(Integer.MAX_VALUE);
        }
        catch(Exception e){
            System.out.println("Unable to connect to Bridges");
        }

        for(int i = 0; i < 5; i++) {

            ActorMovieIMDB entry = movieData.get(i);
            System.out.println("" + i + ".  " + entry.getActor() + " was in " + entry.getMovie());
        }

        for (ActorMovieIMDB i: movieData) {
            if(i.getMovie().equals("Being_John_Malkovich_(1999)")){
                filteredMovieList.add(i);
            }
        }

        filteredMovieList.sort(new ActorComparator());

        for (ActorMovieIMDB i: filteredMovieList) {
            System.out.println(i.getActor());
        }

    }


}