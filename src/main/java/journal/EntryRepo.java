package journal;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EntryRepo extends MongoRepository<Entry, String> {
    Entry findById(String id);
    List<Entry> findByAuthorId(String authorId);
}
