import bridges.data_src_dependent.ActorMovieIMDB;

import java.util.*;

public class ActorComparator implements Comparator<ActorMovieIMDB> {

    @Override
    public int compare(ActorMovieIMDB o1, ActorMovieIMDB o2) {
        if(o1.getActor().compareTo(o2.getActor()) > 0){
            return 1;
        }
        else if(o1.getActor().compareTo(o2.getActor()) < 0){
            return -1;
        }
        else{
            return 0;
        }

    }
}
