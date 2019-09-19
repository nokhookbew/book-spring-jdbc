package book.dao;

import book.model.Book;
import book.model.BookRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BookDaoImp implements BookDao {

    private JdbcTemplate jdbcTemplate;

    public BookDaoImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Book book) {
        String query = "INSERT INTO book (id, name, price) VALUES (?, ?, ?);";
        Object[] data = new Object[]
                { book.getId(), book.getName(), book.getPrice() };
        jdbcTemplate.update(query, data);

    }

    @Override
    public void update(int id, Book book) {
        String update = "UPDATE book SET id = ?, name = ?, price = ? WHERE id = ?;";
        Object[] data = new Object[]
                { book.getId(), book.getName(), book.getPrice(), id};
        jdbcTemplate.update(update, data);
    }

    @Override
    public void deleteById(int id) {
        String delete = "DELETE FROM book WHERE id = ?";
        Object[] data = new Object[]
                {id};
        jdbcTemplate.update(delete, data);
    }

    @Override
    public Book findById(int id) {
        String query = "SELECT * FROM book WHERE id = " + id;
        Book book = jdbcTemplate.queryForObject(query, new BookRowMapper());
        return book;

    }

    @Override
    public List<Book> findAll() {
        String query = "SELECT * FROM book";
        List<Book> books = jdbcTemplate.query(query, new BookRowMapper());
        return books;

    }
}
