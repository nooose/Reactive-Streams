package webflux.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Book {
    @Id
    @Column("book_id")
    private Long id;
    private String title;

    @CreatedDate
    private LocalDateTime createdAt;

    @Column("last_modified_at")
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public Book(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public void changeTitle(String title) {
        this.title = title;
    }
}
