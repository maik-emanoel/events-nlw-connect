package br.com.nlw.events.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.nlw.events.dto.SubscriptionRankingItem;
import br.com.nlw.events.model.Event;
import br.com.nlw.events.model.Subscription;
import br.com.nlw.events.model.User;

public interface SubscriptionRepo extends CrudRepository<Subscription, Integer> {
  public Subscription findByEventAndSubscriber(Event evt, User user);

  @Query(value = "select\r\n" + //
      "  count(indication_user_id) as quantidade,\r\n" + //
      "  indication_user_id,\r\n" + //
      "  user_name\r\n" + //
      "from\r\n" + //
      "  tbl_subscription tbls\r\n" + //
      "  inner join tbl_user on tbls.indication_user_id = tbl_user.user_id\r\n" + //
      "where\r\n" + //
      "  indication_user_id is not null\r\n" + //
      "  and event_id = 5\r\n" + //
      "group by\r\n" + //
      "  indication_user_id\r\n" + //
      "order by\r\n" + //
      "  quantidade DESC", nativeQuery = true)
  public List<SubscriptionRankingItem> generateRanking(@Param("eventId") Integer eventId);
}
