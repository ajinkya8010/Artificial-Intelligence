% Define example books with title, author, genre, average user rating
% and market_price, publication year
book('The Great Gatsby', 'Rocky', fiction, 4.5,500,2012).
book('To Kill a Mockingbird', 'John', fiction, 4.8,399,2020).
book('Pride and Prejudice', 'Smith', fiction, 4.6,785,2022).
book('1984', 'Dave', science_fiction, 4.3,199,2011).
book('Brave New World', 'Samuel', science_fiction, 4.2,229,2022).
book('The Martian', 'Ricky', science_fiction, 4.7,669,2023).
book('The Da Vinci Code', 'Greme', mystery, 4.0,999,2021).
book('Sherlock Holmes', 'Conan', mystery, 4.5,779,2022).
book('Gone Girl', 'Jim', mystery, 4.1,299,2019).
book('Sapiens: A Brief History of Humankind', 'Romero', nonfiction, 4.6,99,2024).
book('The Power of Habit', 'Seth', nonfiction, 4.4,239,2021).
book('Educated: A Memoir', 'Marlon', nonfiction, 4.7,449,2020).
book('The Da Vinci Code', 'Marlon', drama, 4.0,999,2024).
book('Sherlock Holmes', 'Greme', drama, 4.5,779,2023).
book('Gone Girl', 'Jim', horror, 4.1,299,2019).
book('Sapiens: A Brief History of Humankind', 'Romero', horror, 4.6,99,2024).
book('The Power of Habit', 'Smith', romantic, 4.4,239,2021).
book('Educated: A Memoir', 'Jinx', romantic, 4.7,449,2020).

% Predicate to recommend books based on user's preferred genre
recommend_book(Genre, Book) :-
    book(Book, _, Genre, _).

% Predicate to recommend books based on user's preferred author
recommend_book_by_author(Author, Book) :-
    book(Book, Author, _, _).

% Predicate to recommend top rated books in a genre
top_rated_books(Genre, Book) :-
    book(Book, _, Genre, Rating),
    Rating >= 4.5.

% Predicate to remmend books above or equal to input ratings.
top_rated_books(RatingThreshold, Book) :-
    book(Book, _, _, Rating),
    Rating >= RatingThreshold.

% Predicate to recomment books similar to input book(based on author or
% genre)
similar_books(Book, SimilarBook) :-
    (   book(Book, _, Genre, _, _),
        book(SimilarBook, _, Genre, _, _)
    ;   book(Book, Author, _, _, _),
        book(SimilarBook, Author, _, _, _)
    ),
    Book \= SimilarBook.

under_budget(Book,Genre,Budget):-
    book(Book, _, Genre, _,Price),
    Budget>=Price.

recommend_by_price_range(Book, MinPrice, MaxPrice) :-
    book(Book, _, _, _, Price),
    Price >= MinPrice,
    Price =< MaxPrice.

latest_books(Book,Year):-
    book(Book, _, _, _, _, Y),
    Y>=Year.

% Latest (last 2 years) high rated books of input genre
latest_books_genre(Book,Genre):-
    book(Book, _, G, Rating, _, Y),
    Genre=G,
    Y>=2022,
    Rating>=4.5.

% Latest high rated books of given author
latest_books_author(Book,Author):-
    book(Book, A, _, Rating, _, Y),
    A=Author,
    Y>=2022,
    Rating>=4.5.






