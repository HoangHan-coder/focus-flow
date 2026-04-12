
INSERT INTO users (password, email, full_name, role, user_status, create_at)
VALUES
    ('$2a$12$.oxcff34yDqi51tZVoCfYO0NLa17aZCJN3t/WgWkvYusE.h7G6Bsa', 'user1@gmail.com', N'Nguyen Van A', 'User', 'ACTIVE', GETDATE()),

    ('$2a$12$.oxcff34yDqi51tZVoCfYO0NLa17aZCJN3t/WgWkvYusE.h7G6Bsa', 'user2@gmail.com', N'Tran Thi B', 'User', 'ACTIVE', GETDATE()),

    ('$2a$12$.oxcff34yDqi51tZVoCfYO0NLa17aZCJN3t/WgWkvYusE.h7G6Bsa', 'admin@gmail.com', N'Le Van C', 'Admin', 'ACTIVE', GETDATE()),

    ('$2a$12$.oxcff34yDqi51tZVoCfYO0NLa17aZCJN3t/WgWkvYusE.h7G6Bsa', 'manager@gmail.com', N'Pham Thi D', 'Admin', 'ACTIVE', GETDATE()),

    ('$2a$12$.oxcff34yDqi51tZVoCfYO0NLa17aZCJN3t/WgWkvYusE.h7G6Bsa', 'test@gmail.com', N'Hoang Van E', 'User', 'BANNED', GETDATE());