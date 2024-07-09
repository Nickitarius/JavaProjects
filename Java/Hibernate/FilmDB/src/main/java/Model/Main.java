package Model;

import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query1 = session.createQuery("select m.name, m.year, m.directors.size from Movie m");
        System.out.println("\n---Request 1 - All films from DB: ---\n");
        ArrayList<Object> l1 = (ArrayList<Object>) query1.getResultList();
        for (int i = 0; i < l1.size(); i++) {
            System.out.println("---Name: " + ((Object[]) l1.get(i))[0] + " Year: "
                    + ((Object[]) l1.get(i))[1] + " Directors number: " + ((Object[]) l1.get(i))[2] + "---");
        }
        System.out.println();
        Query query2 = session.createQuery("select r.directors from Movie r where r.year>=:yearMin and r.year<=:yearMax")
                .setParameter("yearMin", 1980).setParameter("yearMax", 1989);
        ArrayList<Director> l2 = (ArrayList<Director>) query2.getResultList();
        System.out.println("\n---Request 2 - All directors from 80's: ---\n");
        for (Director d : l2) {
            System.out.println("---" + d + "---");
        }

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Movie> cq3 = cb.createQuery(Movie.class);
        Root<Movie> r3 = cq3.from(Movie.class);
        cq3.select(r3);
        cq3.where(cb.between(r3.get("year"), 1995, 2000));
        //cq3.where(cb.greaterThanOrEqualTo(r3.get("year"), 1995));
        Query query3 = session.createQuery(cq3);
        System.out.println("\n---Request 3 - Films(1995-2000): ---");
        ArrayList<Movie> l3 = (ArrayList<Movie>) query3.getResultList();
        //Дополнительные запросы происходят от getDirectors???
        for (Movie m : l3) {
            System.out.println("---Name: " + m.getName() + " Year: " + m.getYear()
                    + " Directors:" + m.getDirectors() + " Rank:" + m.getRank()
                    + "Id: " + m.getId() + "---");
        }
        System.out.println();

        //David'ов в БД двое. Я просто взял средний рейтинг всех фильмов всех David'ов
        CriteriaQuery<Movie> cq4 = cb.createQuery(Movie.class);
        Root<Movie> r4 = cq4.from(Movie.class);
        Join<Movie, Director> j4 = r4.join("directors");
        cq4.select(r4);
        cq4.where(cb.equal(j4.get("firstName"), "David"));
        Query query4 = session.createQuery(cq4);
        ArrayList<Movie> l4 = (ArrayList<Movie>) query4.getResultList();
        int rating = 0;
        for (int i = 0; i < l4.size(); i++) {
            rating += l4.get(i).getRank();
        }
        System.out.println("\n---Request 4 - Average rating of films directed by some 'David(s)': "
                + (rating / l4.size()) + " ---\n");

        CriteriaQuery<Movie> cq5 = cb.createQuery(Movie.class);
        Root<Movie> r5 = cq5.from(Movie.class);
        Predicate yearPredicate = cb.greaterThan(r5.get("year"), 2000);
        Predicate rankPredicate = cb.isNotNull(r5.get("rank"));
        cq5.where(cb.and(yearPredicate, rankPredicate));
        Query query5 = session.createQuery(cq5);
        ArrayList<Movie> l5 = (ArrayList<Movie>) query5.getResultList();
        System.out.println("\n---Request 5 - last names of directors of ranked films after 2000: ---\n");
        for (Movie m : l5) {
            System.out.print("---");
            for (Director d : m.getDirectors()) {
                System.out.print(d.getLastName() + " ");
            }
            System.out.println("---");
        }

        CriteriaQuery<Movie> cq6 = cb.createQuery(Movie.class);
        Root<Movie> r6 = cq6.from(Movie.class);
        Order order = cb.desc(r6.get("rank"));
        cq6.orderBy(order);
        //cq6.where(cb.greaterThanOrEqualTo(r6.get("rank"), 8));
        Query query6 = session.createQuery(cq6).setMaxResults(20);
        ArrayList<Movie> l6 = (ArrayList<Movie>) query6.getResultList();
        System.out.println("\n---Request 6 - " + l6.size() + " highest ranking films: ---\n");
        for (Movie m : l6) {
            System.out.println("---Name: " + m.getName() + " Rank: " + m.getRank() + "---");
        }

        session.close();
        System.exit(0);
    }
}
