INSERT INTO books (book_id, title, author, isbn, total_quantity, available_quantity, created_at, updated_at)
SELECT 
    REPLACE(UUID(),'-',''),
    'Clean Code',
    'Robert C. Martin',
    '9780132350884',
    5,
    5,
    NOW(),
    NOW()
WHERE NOT EXISTS (SELECT 1 FROM books WHERE isbn = '9780132350884');

INSERT INTO books (book_id, title, author, isbn, total_quantity, available_quantity, created_at, updated_at)
SELECT 
    REPLACE(UUID(),'-',''),
    'Design Patterns',
    'Erich Gamma',
    '9780201633610',
    3,
    3,
    NOW(),
    NOW()
WHERE NOT EXISTS (SELECT 1 FROM books WHERE isbn = '9780201633610');
INSERT INTO books (book_id, title, author, isbn, total_quantity, available_quantity, created_at, updated_at)
SELECT
    REPLACE(UUID(),'-',''),
    'The Pragmatic Programmer',
    'Andrew Hunt & David Thomas',
    '9780201616224',
    4,
    4,
    NOW(),
    NOW()
    WHERE NOT EXISTS (
    SELECT 1 FROM books WHERE isbn = '9780201616224'
);


INSERT INTO books (book_id, title, author, isbn, total_quantity, available_quantity, created_at, updated_at)
SELECT 
    REPLACE(UUID(),'-',''),
    'Refactoring',
    'Martin Fowler',
    '9780134757599',
    4,
    4,
    NOW(),
    NOW()
WHERE NOT EXISTS (SELECT 1 FROM books WHERE isbn = '9780134757599');

-- Insert sample borrow slips
INSERT INTO borrow_slips (slip_id, borrower_name, student_id, contact_email, contact_phone, books, borrow_date, due_date, status, created_at, updated_at)
VALUES (
    REPLACE(UUID(),'-',''),
    'John Doe',
    'ST001',
    'john.doe@example.com',
    '0123456789',
    'Clean Code',
    CURDATE(),
    DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL 14 DAY), '%Y-%m-%d'),
    'Active',
    NOW(),
    NOW()
);

INSERT INTO borrow_slips (slip_id, borrower_name, student_id, contact_email, contact_phone, books, borrow_date, due_date, status, created_at, updated_at)
VALUES (
    REPLACE(UUID(),'-',''),
    'Jane Smith',
    'ST002',
    'jane.smith@example.com',
    '0987654321',
    'Design Patterns, Refactoring',
    DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 7 DAY), '%Y-%m-%d'),
    DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL 7 DAY), '%Y-%m-%d'),
    'Active',
    NOW(),
    NOW()
); 